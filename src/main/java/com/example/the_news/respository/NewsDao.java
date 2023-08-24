package com.example.the_news.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.the_news.entity.News;

@Repository
public interface NewsDao extends JpaRepository<News, Integer> {

	public List<News> findByOrderByUpdateDateDesc();
	
}
