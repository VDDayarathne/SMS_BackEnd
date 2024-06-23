package com.sms.sms.service;

import com.sms.sms.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sms.sms.entity.Notification;

import java.util.Date;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findTop15ByIsAdminOnlyFalseOrderByCreatedAtDesc();
    }

    public Notification createNotification(String message, Boolean isAdminOnly, String title) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setTitle(title);
        notification.setIsAdminOnly(false);
        notification.setCreatedAt(new Date());
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }



}
