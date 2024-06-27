package com.sms.sms.controller;

import com.sms.sms.entity.SportSchedule;
import com.sms.sms.service.SportScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sport-schedules")
public class SportScheduleController {
    @Autowired
    private SportScheduleService sportScheduleService;

    @GetMapping
    public List<SportSchedule> getAllSchedules() {
        return sportScheduleService.getAllSchedules();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public SportSchedule createSchedule(@RequestBody SportSchedule schedule) {
        return sportScheduleService.createSchedule(schedule);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SportSchedule updateSchedule(@PathVariable Long id, @RequestBody SportSchedule schedule) {
        return sportScheduleService.updateSchedule(id, schedule);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteSchedule(@PathVariable Long id) {
        sportScheduleService.deleteSchedule(id);
    }

    @GetMapping("/{date}")
    public List<SportSchedule> getSchedulesByDate(@PathVariable String date) {
        return sportScheduleService.getSchedulesByDate(date);
    }

}
