package com.example.the_news.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "news")
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_number")
	private Integer serialNumber;
	
//	標題
	@Column(name = "title")
	private String title;
	
//	發布日期(預設當天)
	@Column(name = "update_date")
	private LocalDate updateDate = LocalDate.now();
	
//	標籤
	@Column(name = "tags")
	private String tags;
	
//	內容
	@Column(name = "content")
	private String content;

//	----------------------------------
	
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}

	public News(Integer serialNumber, String title, LocalDate updateDate, String tags, String content) {
	super();
	this.serialNumber = serialNumber;
	this.title = title;
	this.updateDate = updateDate;
	this.tags = tags;
	this.content = content;
}

	public News(String title, LocalDate updateDate, String tags, String content) {
		super();
		this.title = title;
		this.updateDate = updateDate;
		this.tags = tags;
		this.content = content;
	}

	public Integer getSerialNumber() {
		return serialNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
}
