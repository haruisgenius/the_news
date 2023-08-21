package com.example.the_news;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.the_news.entity.News;
import com.example.the_news.entity.Tags;
import com.example.the_news.respository.NewsDao;
import com.example.the_news.respository.TagsDao;
import com.example.the_news.service.ifs.NewsService;
import com.example.the_news.vo.NewsResponse;

@SpringBootTest(classes = TheNewsApplication.class)
public class NewsTest {
	
	@Autowired
	private NewsDao nDao;
	
	@Autowired
	private TagsDao tDao;
	
	@Autowired
	private NewsService nService;
	
	@Test
	public void createTagsTest() {
		NewsResponse res1 = nService.createTags("");
		System.out.println(res1.getMessage());
		NewsResponse res2 = nService.createTags("DVD");
		System.out.println(res2.getMessage());
	}
	
	@Test
	public void createNewsTest() {
		NewsResponse res1 = nService.createNews("New Single Release!!", null, "aaa", "test res1");
		System.out.println(res1.getMessage());
		NewsResponse res2 = nService.createNews("AKB48 2023 Live in Budokan DVD Release!!", LocalDate.of(2023, 8, 27), "DVD", "test res2");
		System.out.println(res2.getMessage());
		NewsResponse res3 = nService.createNews("SixTONES 2022 Live DVD Release!!", null, "DVD", "test res3");
		System.out.println(res3.getMessage());
	}

	@Test
	public void deleteTagsTest() {
		List<Tags> deleteTags1 = new ArrayList<>(Arrays.asList(new Tags("aaa")));
		NewsResponse res1 = nService.deleteTags(deleteTags1);
		System.out.println(res1.getMessage());
		List<Tags> deleteTags2 = new ArrayList<>(Arrays.asList(new Tags("CD")));
		NewsResponse res2 = nService.deleteTags(deleteTags2);
		System.out.println(res2.getMessage());
		List<Tags> deleteTags3 = new ArrayList<>(Arrays.asList(new Tags("DVD")));
		NewsResponse res3 = nService.deleteTags(deleteTags3);
		System.out.println(res3.getMessage());
	}
	
	@Test
	public void updateNewsTest() {
		NewsResponse res1 = nService.updateTags(10, "update", null, "DVD", "update");
		System.out.println(res1.getMessage());
		NewsResponse res2 = nService.updateTags(1, "", null, null, null);
		System.out.println(res2.getMessage());
		NewsResponse res3 = nService.updateTags(2, "update", LocalDate.of(2023, 3, 1), "DVD", "emm");
		System.out.println(res3.getMessage());
		NewsResponse res4 = nService.updateTags(1, "update test", LocalDate.of(2024, 1, 12), "ccc", "test will not pass");
		System.out.println(res4.getMessage());
		NewsResponse res5 = nService.updateTags(2, "update test 2", null, "DVD", null);
		System.out.println(res5.getMessage());
		NewsResponse res6 = nService.updateTags(3, "update", LocalDate.of(2023, 9, 23), "DVD", "update update update");
		System.out.println(res6.getMessage());
		NewsResponse res7 = nService.updateTags(3, "update", LocalDate.of(2023, 9, 23), "apple", "update update update");
		System.out.println(res7.getMessage());
	}
	
	@Test
	public void findAllNewsTest() {
		NewsResponse res1 = nService.findAllNews();
		for(News res : res1.getNewsList()) {
			System.out.println(res.getTitle());
		}
	}
	
	@Test
	public void findAllTagsTest() {
		NewsResponse res1 = nService.findAllTags();
		for(Tags res : res1.getTagsList()) {
			System.out.println(res.getTags());
		}
	}
	
}
