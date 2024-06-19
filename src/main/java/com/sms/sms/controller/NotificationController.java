package com.sms.sms.controller;


import com.sms.sms.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.sms.sms.entity.Notification;


import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/{getNotifications}")
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @PostMapping("/{addNotification}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification.getMessage(), false,notification.getTitle());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
    }

}
