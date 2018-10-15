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
import Post.Exception.WritePostFailException;
import Post.Model.Post;
import Post.Model.PostContent;
import Post.Model.PostData;

public class WriteNoticeService {
	private static WriteNoticeService instance = new WriteNoticeService();

	private WriteNoticeService() {
	}

	public static WriteNoticeService getInstance() {
		return instance;
	}

	public int writeNotice(NoticeData writeReq) throws SQLException, WriteNoticeFailException {
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		NoticeContentDAO noticeContentDAO = NoticeContentDAO.getInstance();
		try (Connection conn = ConnectionProvider.getConnection()) {
			conn.setAutoCommit(false);
			try {
			Notice notice = writeReq.getNotice();
			noticeDAO.insertNotice(conn, notice);
			
			int noticeId = noticeDAO.selectLatestNoticeId(conn);
			if (noticeId == 0) {
				conn.rollback();
				throw new WritePostFailException("게시글 삽입 실패");
			}
			System.out.println("게시글 삽입 성공");

			NoticeContent noticeContent = null;
			if (writeReq.getNoticeContent().getImageNames() != null) {
				String imageName = "";
				for (int i = 0; i < writeReq.getNoticeContent().getImageNames().size(); i++) {
					imageName += writeReq.getNoticeContent().getImageNames().get(i);

					if (i < writeReq.getNoticeContent().getImageNames().size() - 1) {
						imageName += "?";
					}
				}
				System.out.println(imageName);
				noticeContent = new NoticeContent(noticeId, writeReq.getNoticeContent().getContent(),
						writeReq.getNoticeContent().getVideo_link(), imageName);
			} else {
				noticeContent = new NoticeContent(noticeId, writeReq.getNoticeContent().getContent(),
						writeReq.getNoticeContent().getVideo_link());
			}
			System.out.println("내용 삽입 시도");
			int ret = noticeContentDAO.insertNoticeContent(conn, noticeContent);
			System.out.println("내용 삽입 성그옹");
			if (ret == 0) {
				conn.rollback();
				throw new WriteNoticeFailException("Content 삽입 실패");
			}

			conn.commit();
			return noticeId;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SQLException("");
			}
		}
	}
}
