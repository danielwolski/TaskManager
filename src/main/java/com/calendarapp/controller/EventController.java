package com.calendarapp.controller;

import java.net.http.HttpResponse;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calendarapp.mapper.EventMapper;
import com.calendarapp.model.Event;
import com.calendarapp.rest.RestEvent;
import com.calendarapp.service.EventService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/events")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;
   
    
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from CalendarApp!";
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody RestEvent restEvent) {
        log.info("Received create event request {}", restEvent);
        Event createdEvent = eventService.createEvent(restEvent);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(createdEvent);
    }
}
