package com.sms.sms.service;

import com.sms.sms.entity.SportSchedule;
import com.sms.sms.repo.SportScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportScheduleService {
    @Autowired
    private SportScheduleRepository sportScheduleRepository;

    public List<SportSchedule> getAllSchedules() {
        return sportScheduleRepository.findAll();
    }

    public SportSchedule createSchedule(SportSchedule schedule) {
        return sportScheduleRepository.save(schedule);
    }

    public SportSchedule updateSchedule(Long id, SportSchedule schedule) {
        SportSchedule existingSchedule = sportScheduleRepository.findById(id).orElseThrow();
        existingSchedule.setDate(schedule.getDate());
        existingSchedule.setVenue(schedule.getVenue());
        existingSchedule.setTime(schedule.getTime());
        existingSchedule.setSport(schedule.getSport());
        existingSchedule.setGround(schedule.getGround());
        return sportScheduleRepository.save(existingSchedule);
    }

    public void deleteSchedule(Long id) {
        sportScheduleRepository.deleteById(id);
    }
}
