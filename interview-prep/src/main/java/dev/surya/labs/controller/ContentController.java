package dev.surya.labs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dev.surya.labs.entity.Topic;
import dev.surya.labs.service.impl.TopicService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ContentController {

	
	@Autowired
	private TopicService topicService;

	

	// handler method to handle list home and return mode and view
	@GetMapping("/home")
	public String listConstituencies(Model model) {
		List<Topic> list = topicService.getAll();
		List<String> topicList = topicService.getDistinctTopics();
		model.addAttribute("home", list);
		model.addAttribute("topic", new Topic());
		model.addAttribute("topicList", topicList);
		return "home";
	}
	
	// handler method to handle list home and return mode and view
		@GetMapping("/home/topic/{topic}")
		public String listConstituencies(Model model,  @PathVariable String topic) {
			List<Topic> list = topicService.getAllByTopicName(topic);
			List<String> topicList = topicService.getDistinctTopics();
			model.addAttribute("home", list);
			model.addAttribute("topic", new Topic());
			model.addAttribute("topicList", topicList);
			return "home";
		}

	// handler method to handle list home and return mode and view
	@GetMapping("/index")
	public String index(HttpSession session, Model model) {
		List<Topic> list = topicService.getAll();
		model.addAttribute("home", list);
		model.addAttribute("topic", new Topic());
		return "index";
	}

	

	@GetMapping("/home/active/{id}")
	public String active(HttpSession session, Model model, @PathVariable Long id) {
		update(session, id, true);
		return "redirect:/home";
	}
	
	private void update(HttpSession session, Long id, boolean isActive) {
		Topic topic = topicService.getById(id);
		topic.setActive(isActive? "Y": "N");
		topicService.update(topic);
		
	}

	@GetMapping("/home/inactive/{id}")
	public String inactive(HttpSession session, Model model, @PathVariable Long id) {
		update(session, id, false);
		return "redirect:/home";
	}
	
	@GetMapping("/home/delete/{id}")
	public String delete(HttpSession session, Model model, @PathVariable Long id) {
		topicService.delete(id);
		return "redirect:/home";
	}
	
	@GetMapping("/home/clearAll")
	public String clearAll(HttpSession session, Model model) {
		topicService.clearAll();
		return "redirect:/home";
	}
	
	@GetMapping("/home/close/{id}")
	public String close(HttpSession session, Model model, @PathVariable Long id) {
		update(session, id, false);
		return "redirect:/index";
	}

	@PostMapping("/home/save")
	public String create(Model model, Topic topic) {
		topicService.create( topic);
		return "redirect:/home";
	}
}
