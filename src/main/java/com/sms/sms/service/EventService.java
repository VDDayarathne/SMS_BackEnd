package com.sms.sms.service;


import com.sms.sms.entity.Event;
import com.sms.sms.repo.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
