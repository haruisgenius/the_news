package com.example.the_news.vo;

import java.time.LocalDate;

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
	
}
