package com.calendarapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.calendarapp.mapper.EventMapper;
import com.calendarapp.model.Event;
import com.calendarapp.repository.EventRepository;
import com.calendarapp.rest.RestCreateEvent;
import com.calendarapp.rest.RestEvent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class EventService {

    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    public Event createEvent(RestCreateEvent restEvent) {
        Event event = eventMapper.restCreateEventToEvent(restEvent);
        //validate(event)
        Event savedEvent = eventRepository.save(event);
        log.info("Event saved: {}", savedEvent);
        return savedEvent;
    }

    public List<RestEvent> getAllEvents() {
        return eventMapper.eventListToEventRestList(eventRepository.getAll());
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public void updateEvent(RestEvent restEvent) {
        Event event = eventMapper.eventRestToEvent(restEvent);
        eventRepository.updateEvent(event);
    }
    
}
