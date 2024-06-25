package com.sms.sms.service;


import com.sms.sms.entity.Event;
import com.sms.sms.repo.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepo eventRepo;

    public List<Event> getAllEvents() {
        return eventRepo.findAll();
    }

    public Event createEvent(Event event) {
        return eventRepo.save(event);
    }

    public Event getEvent(Long id) {
        return eventRepo.findById(id).orElseThrow();
    }

    public Event updateEvent(Long id, Event event) {
        Event existingEvent = getEvent(id);
        existingEvent.setTitle(event.getTitle());
        existingEvent.setDescription(event.getDescription());
        existingEvent.setDate(event.getDate());
        return eventRepo.save(existingEvent);
    }

    public void deleteEvent(Long id) {
        eventRepo.deleteById(id);
    }
    public List<Event> getEventsByUserEmail(String email) {
        return eventRepo.findByEmail(email);
    }

    @Scheduled(fixedDelay = 3600000) // run every hour
    public void removeExpiredEvents() {
        Date oneWeekAgo = new Date(System.currentTimeMillis() - 604800000); // 1 week ago
        eventRepo.deleteByPublishedDateBefore(oneWeekAgo);
    }

}
