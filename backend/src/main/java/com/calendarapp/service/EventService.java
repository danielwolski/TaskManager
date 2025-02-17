package com.calendarapp.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.calendarapp.mapper.EventMapper;
import com.calendarapp.model.Event;
import com.calendarapp.repository.EventRepository;
import com.calendarapp.rest.event.RestCreateEvent;
import com.calendarapp.rest.event.RestEvent;
import com.calendarapp.rest.event.RestEventDetails;

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
        return eventMapper.eventListToEventRestList(eventRepository.findAll());
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public RestEventDetails getEventDetails(Long id) {
        return eventMapper.eventToEventDetails(eventRepository.getById(id));
    }

    public List<RestEventDetails> getEventDetailsByDay(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        return eventMapper.eventListToRestEventDetailsList(eventRepository.findAllByStartTimeBetween(startOfDay, endOfDay));
    }
    
}
