package com.example.the_news.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.the_news.entity.Tags;

public interface TagsDao extends JpaRepository<Tags, Integer> {

}
