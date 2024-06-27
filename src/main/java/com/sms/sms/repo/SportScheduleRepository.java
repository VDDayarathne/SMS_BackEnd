package com.sms.sms.repo;

import com.sms.sms.entity.SportSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SportScheduleRepository extends JpaRepository<SportSchedule, Long> {
    List<SportSchedule> findByDate(String date);
    List<SportSchedule> findByVenue(String venue);
    List<SportSchedule> findByDescription(String Description);
}
