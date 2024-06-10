package com.sms.sms.repo;

import com.sms.sms.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EventRepo extends JpaRepository<Event, Long> {

}
