package com.sms.sms.repo;

import com.sms.sms.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepo extends JpaRepository<Event, Long> {

}
