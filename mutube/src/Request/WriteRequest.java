package Request;

import java.util.Map;

import Post.Model.Writer;

public class WriteRequest {
	private Writer writer;
	private String title;
	private String content;
	private String genre;
	private String musician;
	private String instrument;

	public WriteRequest(Writer writer, String title, String content, String genre, String musician, String instrument) {
		super();
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.genre = genre;
		this.musician = musician;
		this.instrument = instrument;
	}

	public String getGenre() {
		return genre;
	}

	public String getMusician() {
		return musician;
	}

	public String getInstrument() {
		return instrument;
	}

	public Writer getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if(title == null || title.trim().isEmpty()) {
			errors.put("title", true);
		}
	}

}
