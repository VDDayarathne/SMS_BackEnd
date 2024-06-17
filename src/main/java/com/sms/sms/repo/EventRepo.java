package com.sms.sms.repo;

import com.sms.sms.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EventRepo extends JpaRepository<Event, Long> {
    List<Event> findByUserEmail(String userEmail);
}
