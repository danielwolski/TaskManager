package com.calendarapp.service;

import org.springframework.stereotype.Service;

import com.calendarapp.mapper.EventMapper;
import com.calendarapp.model.Event;
import com.calendarapp.repository.EventRepository;
import com.calendarapp.rest.RestEvent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class EventService {

    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    public Event createEvent(RestEvent restEvent) {
        Event event = eventMapper.eventRestToEvent(restEvent);
        //validate(event)
        Event savedEvent = eventRepository.save(event);
        log.info("Event saved: {}", savedEvent);
        return savedEvent;
    }
    
}
