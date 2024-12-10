package com.calendarapp.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calendarapp.model.Event;
import com.calendarapp.rest.RestCreateEvent;
import com.calendarapp.rest.RestEvent;
import com.calendarapp.rest.RestEventDetails;
import com.calendarapp.service.EventService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/events")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;
   
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody RestCreateEvent restCreateEvent) {
        log.info("Received create event request {}", restCreateEvent);
        Event createdEvent = eventService.createEvent(restCreateEvent);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(createdEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteEvent(@PathVariable Long id) {
        log.info("Received delete event request {}", id);
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<Response> updateEvent(@RequestBody RestEvent restEvent) {
        log.info("Received update event request {}", restEvent);
        eventService.updateEvent(restEvent);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping
    public ResponseEntity<List<RestEvent>> getAllEvents() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestEventDetails> getEventDetails(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(eventService.getEventDetails(id));
    }
}
