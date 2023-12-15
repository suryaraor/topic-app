package dev.surya.labs.entity;

import jakarta.persistence.*;

@Entity

@Table(name = "Content")
public class Content {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "topic", nullable = false)
	private String topic;
	
	@Column(name = "content", length = 2048)
	private String content;
	
	@Column(name = "active")
	private String active;
	
	
	
	public Content() {
		
	}
	
	public Content(String id, String topic, String content, String active) {
		super();
		this.id = new Long(id);
		this.topic = topic;
		this.content = content;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
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
		return "Content [id=" + id + ", topic=" + topic + ", content=" + content + ", active=" + active + "]";
	}
	
	
}
