package com.sms.sms.controller;


import com.sms.sms.entity.Event;
import com.sms.sms.repo.EventRepo;
import com.sms.sms.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@CrossOrigin(origins = "http://192.168.56.1:3000")
@AllArgsConstructor

public class EventController {
    @Autowired
    private EventRepo eventRepo;
    @Autowired
    private EventService eventService;

    /*@PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventRepo.save(event);
    }*/

    @GetMapping
    public List<Event> getEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping("/{addEvent}")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Long id) {
        return eventService.getEvent(id);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }


}
