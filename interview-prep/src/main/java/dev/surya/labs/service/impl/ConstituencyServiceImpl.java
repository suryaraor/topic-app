package dev.surya.labs.service.impl;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.surya.labs.entity.Content;
import dev.surya.labs.repository.ContentRepository;
import dev.surya.labs.service.ContentService;

@Service
public class ConstituencyServiceImpl implements ContentService{

	private ContentRepository contentRepository;
	
	@Autowired
	private GoogleSheetUpdater googleSheetUpdater;
	
	@Autowired
	private CsvUpdater csvUpdater;
	
	public ConstituencyServiceImpl(ContentRepository constituencyRepository) {
		super();
		this.contentRepository = constituencyRepository;
	}

	@Override
	public List<Content> getAllContent() {
		List<Content> list =  contentRepository.findAll();
		return list;
	}

	@Override
	public Content saveConstituency(Content constituency) {
		return contentRepository.save(constituency);
	}

	@Override
	public Content getContentByID(Long id) {
		return contentRepository.findById(id).get();
	}

	@Override
	public Content update(Content content, String status, String contentText) {
		content = csvUpdater.updateData(content, status, contentText);
		return contentRepository.save(content);
	}
	
	@Override
	public void create(String contentText) {
		Content content = csvUpdater.createData(contentText);
		contentRepository.save(content);
	}
	

}
