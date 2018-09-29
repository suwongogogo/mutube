package Post.Model;

import java.time.LocalDateTime;

public class Post {
	private int postId;
	private int userid;
	private String title;
	private String genre;
	private String musician;
	private String instrument;
	private LocalDateTime write_date;
	private LocalDateTime update_date;
	private int views;

	public Post(int postId, int userid, String title, String genre, String musician, String instrument,
			LocalDateTime write_date, LocalDateTime update_date, int views) {
		super();
		this.postId = postId;
		this.userid = userid;
		this.title = title;
		this.genre = genre;
		this.musician = musician;
		this.instrument = instrument;
		this.write_date = write_date;
		this.update_date = update_date;
		this.views = views;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getMusician() {
		return musician;
	}

	public void setMusician(String musician) {
		this.musician = musician;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public LocalDateTime getWrite_date() {
		return write_date;
	}

	public void setWrite_date(LocalDateTime write_date) {
		this.write_date = write_date;
	}

	public LocalDateTime getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(LocalDateTime update_date) {
		this.update_date = update_date;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

}
