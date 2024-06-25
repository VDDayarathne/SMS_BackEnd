package com.sms.sms.service;

import com.sms.sms.entity.News;
import com.sms.sms.repo.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    public List<News> getAllNews() {
        return newsRepository.findTop10ByOrderByCreatedAtDesc();
    }

    public News createNews(News news) {
        return newsRepository.save(news);
    }

    public News getNewsById(Long id) {
        return newsRepository.findById(id).orElseThrow();
    }

    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }

    public List<News> getLatestNews(int limit) {
        return newsRepository.findTop10ByOrderByCreatedAtDesc();
    }

    @Scheduled(fixedRate = 86400000) // run every 24 hours
    public void expireOldNews() {
        Date oneMonthAgo = new Date(System.currentTimeMillis() - 30 * 24 * 60 * 60 * 1000);
        newsRepository.deleteByCreatedAtBefore(oneMonthAgo);
    }

}