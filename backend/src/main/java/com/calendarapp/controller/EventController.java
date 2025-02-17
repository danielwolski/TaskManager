package com.calendarapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calendarapp.model.Event;
import com.calendarapp.rest.event.RestCreateEvent;
import com.calendarapp.rest.event.RestEvent;
import com.calendarapp.rest.event.RestEventDetails;
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
    
    @GetMapping
    public ResponseEntity<List<RestEvent>> getAllEvents() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestEventDetails> getEventDetails(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(eventService.getEventDetails(id));
    }

    @GetMapping("/by-day")
    public ResponseEntity<List<RestEventDetails>> getEventDetailsByDay(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(eventService.getEventDetailsByDay(date));
    }
}
