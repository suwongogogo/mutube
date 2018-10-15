package Notice.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import Notice.DAO.NoticeContentDAO;
import Notice.DAO.NoticeDAO;
import Notice.Model.Notice;
import Notice.Model.NoticeContent;
import Notice.Model.NoticeData;
import Post.DAO.PostContentDAO;
import Post.DAO.PostDAO;
import Post.Exception.UpdatePostFailExcpetion;
import Post.Model.Post;
import Post.Model.PostContent;
import Post.Model.PostData;
import Post.Service.UpdatePostService;

public class UpdateNoticeService {
	private static UpdateNoticeService instance = new UpdateNoticeService();

	private UpdateNoticeService() {
	}

	public static UpdateNoticeService getInstance() {
		return instance;
	}

	NoticeDAO noticeDAO = NoticeDAO.getInstance();
	NoticeContentDAO noticeContentDAO = NoticeContentDAO.getInstance();

	public NoticeData getNotice(int noticeId) throws SQLException {
		NoticeData noticeData = null;

		try (Connection conn = ConnectionProvider.getConnection()) {

			Notice notice = noticeDAO.selectById(conn, noticeId);
			NoticeContent noticeContent = noticeContentDAO.selectByNoticeId(conn, noticeId);
			noticeData = new NoticeData(notice, noticeContent);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("");
		}

		return noticeData;
	}

	public void update(NoticeData noticeData) throws UpdatePostFailExcpetion, SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			conn.setAutoCommit(false);
			try {
				int updateCnt = noticeDAO.updateNotice(conn, noticeData.getNotice());
				if (updateCnt == 0) {
					conn.rollback();
					throw new UpdatePostFailExcpetion("게시글 수정 실패");
				}

				NoticeContent noticeContent = null;
				if (noticeData.getNoticeContent().getImageNames() != null) {
					String imageName = "";
					for (int i = 0; i < noticeData.getNoticeContent().getImageNames().size(); i++) {
						imageName += noticeData.getNoticeContent().getImageNames().get(i);

						if (i < noticeData.getNoticeContent().getImageNames().size() - 1) {
							imageName += "?";
						}
					}
					noticeContent = new NoticeContent(noticeData.getNotice().getNoticeId(),
							noticeData.getNoticeContent().getContent(), noticeData.getNoticeContent().getVideo_link(),
							imageName);
					updateCnt = noticeContentDAO.updateWithImage(conn, noticeData.getNoticeContent(),
							noticeData.getNotice().getNoticeId());
				} else {
					noticeContent = new NoticeContent(noticeData.getNotice().getNoticeId(),
							noticeData.getNoticeContent().getContent(), noticeData.getNoticeContent().getVideo_link());
					updateCnt = noticeContentDAO.updateNoticeContent(conn, noticeData.getNoticeContent(),
							noticeData.getNotice().getNoticeId());
				}

				if (updateCnt == 0) {
					conn.rollback();
					throw new UpdatePostFailExcpetion("내용 수정 실패");
				}

				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				conn.rollback();
				throw new SQLException("");
			}
		}
	}
}
