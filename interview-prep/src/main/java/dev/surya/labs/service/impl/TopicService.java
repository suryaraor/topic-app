package dev.surya.labs.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.surya.labs.entity.Topic;

@Service
public class TopicService {

	private final String jsonFilePath;
	private final ObjectMapper objectMapper;

	public TopicService(@Value("${json.file.path}") String jsonFilePath) {
		this.jsonFilePath = jsonFilePath;
		this.objectMapper = new ObjectMapper();
	}

	// Create operation
	public void create(Topic topic) {
		List<Topic> topics = readDataFromFile();
		topic.setId(new Long(topics.size()+1));
		topic.setActive("Y");
		topics.add(topic);
		writeDataToFile(topics);
	}

	// Read operation
	public List<Topic> getAll() {
		return readDataFromFile();
	}
	
	// Get all topics by topic name
    public List<Topic> getAllByTopicName(String topicName) {
        List<Topic> allTopics = readDataFromFile();

        // Filter topics by the provided topic name
        return allTopics.stream()
                .filter(topic -> topic.getType().equalsIgnoreCase(topicName))
                .collect(Collectors.toList());
    }

	// Read operation by ID
	public Topic getById(Long id) {
		return readDataFromFile().stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
	}

	// Update operation
	public void update(Topic updatedTopic) {
		List<Topic> topics = readDataFromFile();
		topics.removeIf(e -> e.getId().equals(updatedTopic.getId()));
		topics.add(updatedTopic);
		writeDataToFile(topics);
	}

	// Delete operation
	public void delete(Long id) {
		List<Topic> topics = readDataFromFile();
		topics.removeIf(e -> e.getId().equals(id));
		writeDataToFile(topics);
	}

	// Helper method to read data from the JSON file
	private List<Topic> readDataFromFile() {
		try {
			return objectMapper.readValue(new File(jsonFilePath), new TypeReference<List<Topic>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	// Helper method to write data to the JSON file
	private void writeDataToFile(List<Topic> topics) {
		try {
			objectMapper.writeValue(new File(jsonFilePath), topics);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Get distinct topics
	public List<String> getDistinctTopics() {
		List<Topic> allTopics = readDataFromFile();

		// Extract distinct topics using Java Streams
		return allTopics.stream().map(Topic::getType).distinct().collect(Collectors.toList());
	}

	public void clearAll() {
		List<Topic> topics = readDataFromFile();
		for(Topic topic: topics) {
			topic.setActive("N");
		}
		
		writeDataToFile(topics);
		
	}
}
