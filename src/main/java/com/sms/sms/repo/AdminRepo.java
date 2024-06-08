package com.sms.sms.repo;

import com.sms.sms.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.management.Notification;
import java.util.List;
import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin, Long> {

    @Query("SELECT admin FROM Admin admin WHERE admin.id = ?1")
    Optional<Admin> getAdminByAdminID(String id);

    /*@Query("SELECT n FROM Notification n")
    List<Notification> getNotifications();*/

    @Query("SELECT a.notification FROM Admin a")
    List<String> getNotifications();

}
