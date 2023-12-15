package dev.surya.labs.entity;

//Updated entity class representing the data structure
public class Topic {
	private Long id;
	private String type;
	private String content;
	private String active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Topic [id=" + id + ", type=" + type + ", content=" + content + ", active=" + active + "]";
	}

}
