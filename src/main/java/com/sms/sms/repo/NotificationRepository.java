package com.sms.sms.repo;

import com.sms.sms.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByIsAdminOnlyFalse();
    List<Notification> findTop15ByIsAdminOnlyFalseOrderByCreatedAtDesc();
}
