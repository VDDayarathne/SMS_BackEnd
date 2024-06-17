package com.sms.sms.controller;

import com.sms.sms.entity.Event;
import com.sms.sms.repo.EventRepo;
import com.sms.sms.service.EventService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.sms.sms.service.JWTUtils; // Import the JWTUtils class

@RestController
@RequestMapping("/api/v1/events")
@CrossOrigin(origins = "http://192.168.56.1:3000")
@AllArgsConstructor

public class EventController {
    @Autowired
    private EventRepo eventRepo;
    @Autowired
    private EventService eventService;
    private final JWTUtils jwtUtils; // Create an instance variable for JWTUtils

    /*@PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventRepo.save(event);
    }*/


    @PostMapping("/{addEvent}")
    public Event createEvent(@RequestBody Event event, HttpServletRequest request) {
        String userEmail = getAuthenticatedUser(request);
        if (userEmail == null) {
            throw new RuntimeException("Unauthorized access");
        }
        event.setUserEmail(userEmail);
        return eventService.createEvent(event);
    }

    @GetMapping("/user/{userEmail}")
    public List<Event> getEventsByUserEmail(@PathVariable String userEmail) {
        return eventService.getAllEventsByUserEmail(userEmail);
    }

    /*@GetMapping
    read morepublic List<Event> getEvents() {
        return eventService.getAllEvents();
    }*/

    /*@PostMapping("/{addEvent}")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }*/

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

    private String getAuthenticatedUser(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String userEmail = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);
            userEmail = jwtUtils.extractUsername(jwtToken); // Call the extractUsername method on the JWTUtils instance
        }
        return userEmail;
    }
}