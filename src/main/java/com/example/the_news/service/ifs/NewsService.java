package com.example.the_news.service.ifs;

import java.time.LocalDate;
import java.util.List;

import com.example.the_news.entity.Tags;
import com.example.the_news.vo.NewsResponse;

public interface NewsService {
	
	// 新增消息
	public NewsResponse createNews(String title, LocalDate updateDate, String tags, String content);
	
	// 新增標籤分類
	public NewsResponse createTags(String tag);
	
	// 刪除標籤
	public NewsResponse deleteTags(List<Tags> tagsList);
	
	// 編輯消息
	public NewsResponse updateTags(Integer newsNumber, String title, LocalDate updateDate, String tags, String content);

	// 找所有消息
	public NewsResponse findAllNews();
	
	// 找所有標籤
	public NewsResponse findAllTags();
	
	
}
