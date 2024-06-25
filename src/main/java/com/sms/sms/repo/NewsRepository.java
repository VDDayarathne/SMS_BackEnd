package com.sms.sms.repo;

import com.sms.sms.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findTop10ByOrderByCreatedAtDesc();
    @Modifying
    @Query("DELETE FROM News n WHERE n.createdAt < :date")
    void deleteByCreatedAtBefore(@Param("date") Date date);

}