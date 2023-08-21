package com.example.the_news.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tags")
public class Tags {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tags_number")
	private Integer tagsNumber;
	
	@Column(name = "tags")
	private String tags;
	
	@Column(name = "amount")
	private int amount;

	public Tags() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tags(Integer tagsNumber, String tags, int amount) {
		super();
		this.tagsNumber = tagsNumber;
		this.tags = tags;
		this.amount = amount;
	}

	public Tags(String tags) {
		super();
		this.tags = tags;
	}

	public Integer getTagsNumber() {
		return tagsNumber;
	}

//	public void setTagsNumber(Integer tagsNumber) {
//		this.tagsNumber = tagsNumber;
//	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	

}
