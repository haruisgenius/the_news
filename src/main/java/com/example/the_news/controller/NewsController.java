package com.example.the_news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.the_news.service.ifs.NewsService;
import com.example.the_news.vo.NewsRequest;
import com.example.the_news.vo.NewsResponse;

@CrossOrigin
@RestController
public class NewsController {

	@Autowired
	private NewsService newsService;
	
	@PostMapping(value = "create_news")
	public NewsResponse createNews(@RequestBody NewsRequest nReq) {
		return newsService.createNews(nReq.getTitle(), nReq.getUpdateDate(), nReq.getTags(), nReq.getContent());
	}
	
	@PostMapping(value = "create_tags")
	public NewsResponse createTags(@RequestBody NewsRequest nReq) {
		return newsService.createTags(nReq.getTagsName());
	}
	
	@DeleteMapping(value = "delete_tags")
	public NewsResponse deleteTags(@RequestBody NewsRequest nReq) {
		return newsService.deleteTags(nReq.getTagsList());
	}
	
	@PostMapping(value = "update_news")
	public NewsResponse updateNews(@RequestBody NewsRequest nReq) {
		return newsService.updateNews(nReq.getSerialNumber(), nReq.getTitle(), nReq.getUpdateDate(), nReq.getTags(), nReq.getContent());
	}
	
	@GetMapping(value = "find_all_news")
	public NewsResponse findAllNews(@RequestBody NewsRequest nReq) {
		return newsService.findAllNews();
	}
	
	@GetMapping(value = "find_all_tags")
	public NewsResponse findAllTags(@RequestBody NewsRequest nReq) {
		return newsService.findAllTags();
	}
	
}
