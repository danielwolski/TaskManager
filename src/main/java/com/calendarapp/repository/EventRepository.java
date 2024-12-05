package com.calendarapp.repository;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.calendarapp.exception.EventNotFoundException;
import com.calendarapp.model.Event;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class EventRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public Event save(Event event) {
        try {
            String sql = "INSERT INTO events (title, start_time, end_time) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, event.getTitle(), event.getStartTime(), event.getEndTime());
            log.info("Event saved successfully: {}", event);
        } catch (Exception e) {
            log.error("Error saving event: ", e);
        }
        return event;
    }

    public List<Event> getAll() {
        try {
            String sql = "SELECT * FROM events";
            
            return jdbcTemplate.query(sql, (rs, rowNum) -> {
                Event event = new Event();
                event.setId(rs.getLong("id"));
                event.setTitle(rs.getString("title"));
                event.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
                event.setEndTime(rs.getTimestamp("end_time").toLocalDateTime());
                return event;
            });
        } catch (Exception e) {
            log.error("Error getting all events: ", e);
            return Collections.emptyList();
        }
    }

    public void deleteById(Long id) {
        try {
            String sql = "DELETE FROM events WHERE id = ?";
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected == 0) {
                log.warn("No event found with id: " + id);
                //TODO Use GlobalExceptionHandler when throwing exceptions
            } else {
                log.info("Event with id " + id + " successfully deleted.");
            }
        } catch (Exception e) {
            log.error("Error deleting event with id: " + id, e);
        }
    }

    public void updateEvent(Event event) {
    try {
        String sql = "UPDATE events SET title = ?, start_time = ?, end_time = ? WHERE id = ?";
        
        int rowsAffected = jdbcTemplate.update(sql, 
            event.getTitle(), 
            event.getStartTime(),
            event.getEndTime(),
            event.getId()
        );

        if (rowsAffected == 0) {
            throw new EventNotFoundException("No event found with id: " + event.getId());
        }

        log.info("Event with id " + event.getId() + " successfully updated.");
    } catch (EventNotFoundException e) {
        throw e;
    } catch (Exception e) {
        log.error("Error updating event with id: " + event.getId(), e);
        throw new RuntimeException("Error updating event", e);
    }
}
}
