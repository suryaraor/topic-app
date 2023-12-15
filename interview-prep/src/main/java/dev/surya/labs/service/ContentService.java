package dev.surya.labs.service;

import java.util.List;

import dev.surya.labs.entity.Content;

public interface ContentService {
	List<Content> getAllContent();
	
	Content saveConstituency(Content constituency);
	
	Content getContentByID(Long id);


	Content update(Content content, String status, String contentText);

	void create(String content);
}
