package com.sms.sms.repo;

import com.sms.sms.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findTop10ByOrderByCreatedAtDesc();
}