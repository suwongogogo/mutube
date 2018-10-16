package Post.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.ConnectionProvider;
import Post.DAO.PostContentDAO;
import Post.DAO.PostDAO;
import Post.Model.Post;
import Post.Model.PostContent;
import Post.Model.PostData;
import Post.Exception.UpdatePostFailExcpetion;

public class UpdatePostService {
	private static UpdatePostService instance = new UpdatePostService();

	private UpdatePostService() {
	}

	public static UpdatePostService getInstance() {
		return instance;
	}

	PostDAO postDAO = PostDAO.getInstance();
	PostContentDAO postContentDAO = PostContentDAO.getInstance();

	public PostData getPost(int postId) throws SQLException {
		PostData postData = null;

		try (Connection conn = ConnectionProvider.getConnection()) {

			Post post = postDAO.selectById(conn, postId);
			PostContent postContent = postContentDAO.selectByPostId(conn, postId);
			if(postContent.getImageNamesStr()!=null) {
				String[] names = postContent.getImageNamesStr().split("\\?");
				ArrayList<String> imageNames = new ArrayList<String>();
				for(String name : names) {
					imageNames.add(name);
				}
				postContent.setImageNames(imageNames);
			}else {
				postContent.setImageNames(null);
			}
			postData = new PostData(post, postContent);
		}

		return postData;
	}

	public void update(PostData postData) throws UpdatePostFailExcpetion, SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			conn.setAutoCommit(false);

			int updateCnt = postDAO.update(conn, postData.getPost());
			if (updateCnt == 0) {
				conn.rollback();
				throw new UpdatePostFailExcpetion("게시글 수정 실패");
			}

			PostContent postContent = null;
			if (postData.getPostContent().getImageNames()!=null) {
				String imageName = "";
				for (int i = 0; i < postData.getPostContent().getImageNames().size(); i++) {
					imageName += postData.getPostContent().getImageNames().get(i);

					if (i < postData.getPostContent().getImageNames().size() - 1) {
						imageName += "?";
					}
				}
				System.out.println(imageName+"넣는다!!");
				postContent = new PostContent(postData.getPost().getPostId(), postData.getPostContent().getContent(),
						postData.getPostContent().getVideo_link(), imageName);
				updateCnt = postContentDAO.updateWithImage(conn, postContent,
						postData.getPost().getPostId());
			} else {
				postContent = new PostContent(postData.getPost().getPostId(), postData.getPostContent().getContent(),
						postData.getPostContent().getVideo_link());
				updateCnt = postContentDAO.update(conn, postData.getPostContent(), postData.getPost().getPostId());
			}

			if (updateCnt == 0) {
				conn.rollback();
				throw new UpdatePostFailExcpetion("내용 수정 실패");
			}

			conn.commit();
		}
	}
}
