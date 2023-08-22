package com.example.the_news.vo;

import java.time.LocalDate;
import java.util.List;

import com.example.the_news.entity.Tags;

public class NewsRequest {

//	News Entity
	private Integer serialNumber;
	
	private String title;
	
	private LocalDate updateDate;
	
	private String tags;
	
	private String content;
	
//	Tags Entity
	private Integer tagsNumber;
	
	private String tagsName;
	
	private List<Tags> tagsList;

	public Integer getSerialNumber() {
		return serialNumber;
	}

//	public void setSerialNumber(Integer serialNumber) {
//		this.serialNumber = serialNumber;
//	}

	public String getTitle() {
		return title;
	}

//	public void setTitle(String title) {
//		this.title = title;
//	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

//	public void setUpdateDate(LocalDate updateDate) {
//		this.updateDate = updateDate;
//	}

	public String getTags() {
		return tags;
	}

//	public void setTags(String tags) {
//		this.tags = tags;
//	}

	public String getContent() {
		return content;
	}

//	public void setContent(String content) {
//		this.content = content;
//	}

	public Integer getTagsNumber() {
		return tagsNumber;
	}

//	public void setTagsNumber(Integer tagsNumber) {
//		this.tagsNumber = tagsNumber;
//	}

	public String getTagsName() {
		return tagsName;
	}


//	public void setTagsName(String tagsName) {
//		this.tagsName = tagsName;
//	}
	
	public List<Tags> getTagsList() {
		return tagsList;
	}
	
//	public void setTagsList(List<Tags> tagsList) {
//		this.tagsList = tagsList;
//	}
	
	
}
