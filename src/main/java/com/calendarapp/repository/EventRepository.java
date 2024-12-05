package com.calendarapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.calendarapp.model.Event;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class EventRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public Event save(Event event) {
        log.info("Saving event {}", event);
        try {
            String sql = "INSERT INTO events (title, start_time, end_time) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, event.getTitle(), event.getStartTime(), event.getEndTime());
            log.info("Event saved successfully: {}", event);
        } catch (Exception e) {
            log.error("Error saving event: ", e);
        }
        return event;
    }
}
