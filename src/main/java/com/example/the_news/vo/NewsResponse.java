package com.example.the_news.vo;

import java.util.List;

import com.example.the_news.entity.News;
import com.example.the_news.entity.Tags;

public class NewsResponse {
	
	private News news;
	
	private Tags tags;
	
	private String message;
	
	private List<News> newsList;
	
	private List<Tags> tagsList;

	public NewsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewsResponse(News news, Tags tags, String message, List<News> newsList, List<Tags> tagsList) {
		super();
		this.news = news;
		this.tags = tags;
		this.message = message;
		this.newsList = newsList;
		this.tagsList = tagsList;
	}

	public NewsResponse(String message) {
		super();
		this.message = message;
	}

	public NewsResponse(News news, String message) {
		super();
		this.news = news;
		this.message = message;
	}

	public NewsResponse(Tags tags, String message) {
		super();
		this.tags = tags;
		this.message = message;
	}

	public NewsResponse(String message, List<News> newsList) {
		super();
		this.message = message;
		this.newsList = newsList;
	}

	public NewsResponse(List<Tags> tagsList, String message) {
		super();
		this.tagsList = tagsList;
		this.message = message;
	}

	public News getNews() {
		return news;
	}

//	public void setNews(News news) {
//		this.news = news;
//	}

	public Tags getTags() {
		return tags;
	}

//	public void setTags(Tags tags) {
//		this.tags = tags;
//	}

	public String getMessage() {
		return message;
	}

//	public void setMessage(String message) {
//		this.message = message;
//	}

	public List<News> getNewsList() {
		return newsList;
	}

//	public void setNewsList(List<News> newsList) {
//		this.newsList = newsList;
//	}
	
	public List<Tags> getTagsList() {
		return tagsList;
	}

//	public void setTagsList(List<Tags> tagsList) {
//		this.tagsList = tagsList;
//	}

	
	

}
