package com.sms.sms.controller;

import com.sms.sms.entity.News;
import com.sms.sms.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping
    public List<News> getAllNews() {
        return newsService.getLatestNews(10);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public News createNews(@RequestBody News news) {
        return newsService.createNews(news);
    }

    @GetMapping("/{id}")
    public News getNewsById(@PathVariable Long id) {
        return newsService.getNewsById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.noContent().build();
    }
}