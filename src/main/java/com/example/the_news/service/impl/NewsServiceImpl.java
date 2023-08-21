package com.example.the_news.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.the_news.constants.RtnCode;
import com.example.the_news.entity.News;
import com.example.the_news.entity.Tags;
import com.example.the_news.respository.NewsDao;
import com.example.the_news.respository.TagsDao;
import com.example.the_news.service.ifs.NewsService;
import com.example.the_news.vo.NewsResponse;

@Service
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	private NewsDao newsDao;
	
	@Autowired
	private TagsDao tagsDao;

	// 新增消息
	@Override
	public NewsResponse createNews(String title, LocalDate updateDate, String tags, String content) {
		// 防呆: 輸入標題
		if(!StringUtils.hasText(title)){
//			return new NewsResponse("未輸入title");
			return new NewsResponse("未輸入title");
		}
		
		// 檢查update date若為null > 當天
		if(updateDate == null) {
			updateDate = LocalDate.now();
		}
		// 檢查update date不可在今天之前
		if(updateDate.isBefore(LocalDate.now())) {
			return new NewsResponse("update date錯誤");
		}
		
		// 檢查輸入的標籤是否存在
		List<Tags> allTags = tagsDao.findAll();
		List<String> allTagsStr = new ArrayList<>();
		for(Tags oldTag : allTags) {
			allTagsStr.add(oldTag.getTags());
		}
		if(!allTagsStr.contains(tags)) {
			return new NewsResponse("無此tags");
		}
		
		// 檢查內容有無文字
		if(!StringUtils.hasText(content)) {
			return new NewsResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		// 在專案裡建內文檔案
		String txt = ".txt";
		String fileName = title.concat(txt);
		try(FileOutputStream fos = new FileOutputStream(fileName);){
			fos.write(content.getBytes());
//			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 取得檔案儲存位置 = 內容
		String filePath = new File(fileName).getAbsolutePath();
		
		// 若存在則此tag文章量+1
		for(Tags tag : allTags) {
			if(tag.getTags().equals(tags)) {
				int newsAmount = tag.getAmount();
				tag.setAmount(newsAmount + 1);
			}
		}
		
		tagsDao.saveAll(allTags);
		return new NewsResponse(newsDao.save(new News(title, updateDate, tags, filePath)), RtnCode.SUCCESSFUL.getMessage());
	}

	// 新增標籤
	@Override
	public NewsResponse createTags(String tag) {
		// 防呆: 輸入標籤名稱
		if(!StringUtils.hasText(tag)){
//			return new NewsResponse("未輸入tags");
			return new NewsResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		
		Tags newTag = new Tags(tag);
		// 防呆: 資料庫已有此標籤
		List<Tags> allTags = tagsDao.findAll();
//		List<String> allTagStr = new ArrayList<>();
		if(allTags.size() > 0){
			for(Tags exiTag : allTags) {
				if(exiTag.getTags().equals(tag)){
//				return new NewsResponse("已有此tags");
					return new NewsResponse(RtnCode.DATA_EXISTED.getMessage());
				}
			}
		}
		
		// 有輸入標籤，且資料庫無此標籤 > 存入資料庫
		return new NewsResponse(tagsDao.save(newTag), RtnCode.SUCCESSFUL.getMessage());
	}

	
	// 刪除標籤
	@Override
	public NewsResponse deleteTags(List<Tags> tagsList) {
		// 所有標籤
		List<Tags> allTagsList = tagsDao.findAll();
		// 想刪的標籤
		List<Tags> deleteTagsList = new ArrayList<>();
		
		// 防呆: 判斷請求有無東西
		if(tagsList.size() == 0) {
			return new NewsResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		
		for(Tags tag : tagsList) {
			for(Tags oldTags : allTagsList) {
				// 防呆: 有無此標籤 > 檢查tags名稱
				if(tag.getTags().equals(oldTags.getTags())) {
					// 若此標籤之文章數 > 0，則無法刪除
					if(oldTags.getAmount() > 0) {
						return new NewsResponse(RtnCode.INCORRECT.getMessage());
					}
					// 否則將此標籤加入欲刪標籤清單 deleteTagsList
					deleteTagsList.add(oldTags);
				}
				// 若equals不成立表無此標籤
//				else {
//					return new NewsResponse(RtnCode.NOT_FOUND.getMessage());
//				}
			}
		}
		// 若過濾後的欲刪標籤清單長度為0，但與要求清單長度不符(預設皆符合刪除標準)，表要求清單中含不存在的標籤
		if(deleteTagsList.size() == 0 || deleteTagsList.size() != tagsList.size()) {
			return new NewsResponse(RtnCode.NOT_FOUND.getMessage());
		}
		
		// 刪除allTagsList中符合deleteTagsList內容的標籤
//		for(Tags oldTag : allTagsList) {
//			if(!deleteTagsList.contains(oldTag)) {
//				finalTagsList.add(oldTag);
//			}
//		}
		
		tagsDao.deleteAll(deleteTagsList);
		
		return new NewsResponse(RtnCode.SUCCESSFUL.getMessage());
	}

	
	// 編輯消息
	@Override
	public NewsResponse updateTags(Integer newsNumber, String title, LocalDate updateDate, String tags,
			String content) {

		// 從資料庫撈出消息，檢查有無此消息
		Optional<News> oldNewsOp = newsDao.findById(newsNumber);
		if(oldNewsOp.isEmpty()) {
			return new NewsResponse(RtnCode.NOT_FOUND.getMessage());
		}
		
		News oldNews = oldNewsOp.get();
		// 檢查此消息能否被修改(尚未發布)
		if(!oldNews.getUpdateDate().isAfter(LocalDate.now())) {
			return new NewsResponse(RtnCode.INCORRECT.getMessage());
		}
		
		// 檢查要求
		// 檢查有無輸入新標題
		if(!StringUtils.hasText(title)) {
			return new NewsResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		// 將新標題存入
		oldNews.setTitle(title);
		
		// 檢查發布日期若為空則預設發布日期為今天
		if(updateDate == null) {
			updateDate = LocalDate.now();
		}
		// 發布日期不可早於今天
		if(updateDate.isBefore(LocalDate.now())) {
			return new NewsResponse(RtnCode.INCORRECT.getMessage());
		}
		// 將新發布日期存入
		oldNews.setUpdateDate(updateDate);
		
		// 檢查標籤
		List<Tags> allTagsList = tagsDao.findAll();
		if(!StringUtils.hasText(tags)) {
			return new NewsResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		// 有無此標籤存在
		List<String> allTagsStrList = new ArrayList<>();
		for(Tags allTag : allTagsList) {
			allTagsStrList.add(allTag.getTags());
		}
		if(!allTagsStrList.contains(tags)) {
			return new NewsResponse(RtnCode.NOT_FOUND.getMessage());
		}
		
		// 處理標籤文章數
		for(Tags allTag : allTagsList) {
			// 舊標籤文章數-1
			if(allTag.getTags().equals(oldNews.getTags())) {
				allTag.setAmount(allTag.getAmount() - 1);
			}
		}
		for(Tags allTag : allTagsList) {
			// 新標籤文章數+1
			if(allTag.getTags().equals(tags)) {
				allTag.setAmount(allTag.getAmount() + 1);
			}
		}
		// 將新標籤存入
		oldNews.setTags(tags);
		
		// 檢查內容有無文字
		if(!StringUtils.hasText(content)) {
			return new NewsResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		// 消息內容檔案處理
		String txt = ".txt";
		String fileName = title.concat(txt);
		try(FileOutputStream fos = new FileOutputStream(fileName);) {
			fos.write(content.getBytes());
//			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 存入新檔案位置
		String filePath = new File(fileName).getAbsolutePath();
		oldNews.setContent(filePath);
		
		// 將tags變動存入資料庫
		tagsDao.saveAll(allTagsList);
		// 將消息變動存入資料庫
		return new NewsResponse(newsDao.save(oldNews), RtnCode.SUCCESSFUL.getMessage());
	}

	
	// 找所有消息
	@Override
	public NewsResponse findAllNews() {
		// TODO Auto-generated method stub
		return new NewsResponse(RtnCode.SUCCESSFUL.getMessage(), newsDao.findAll());
	}

	
	// 找所有標籤
	@Override
	public NewsResponse findAllTags() {
		// TODO Auto-generated method stub
		return new NewsResponse(tagsDao.findAll(), RtnCode.SUCCESSFUL.getMessage());
	}
	
	
	
	
	

}
