package com.calendarapp.repository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.calendarapp.exception.EventNotFoundException;
import com.calendarapp.model.Event;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class EventRepository {

    private final JdbcTemplate jdbcTemplate;

    public EventRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public Event save(Event event) {
        String sql = "INSERT INTO events (title, start_time, end_time, description) VALUES (?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, event.getTitle(), event.getStartTime(), event.getEndTime(), event.getDescription());
            log.info("Event saved successfully: {}", event);
            return event;
        } catch (Exception e) {
            log.error("Error saving event: {}", event, e);
            throw new RuntimeException("Failed to save event", e);
        }
    }

    public List<Event> getAll() {
        String sql = "SELECT * FROM events";
        try {
            return jdbcTemplate.query(sql, eventRowMapper);
        } catch (Exception e) {
            log.error("Error fetching all events", e);
            return Collections.emptyList();
        }
    }

    public Event getEventDetails(Long id) {
        String sql = "SELECT * FROM events WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, eventRowMapper, id);
        } catch (Exception e) {
            log.warn("Event not found for id: {}", id);
            return null;
        }
    }

    public List<Event> getEventsByDay(LocalDate date) {
        String sql = "SELECT * FROM events WHERE start_time::date = ?";
        return jdbcTemplate.query(sql, eventRowMapper, date);
    }

    @Transactional
    public void deleteById(Long id) {
        String sql = "DELETE FROM events WHERE id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected == 0) {
                throw new EventNotFoundException("No event found with id: " + id);
            }
            log.info("Event with id {} successfully deleted.", id);
        } catch (Exception e) {
            log.error("Error deleting event with id: {}", id, e);
            throw new RuntimeException("Failed to delete event", e);
        }
    }

    private final RowMapper<Event> eventRowMapper = (rs, rowNum) -> {
        Event event = new Event();
        event.setId(rs.getLong("id"));
        event.setTitle(rs.getString("title"));
        event.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
        event.setEndTime(rs.getTimestamp("end_time").toLocalDateTime());
        event.setDescription(rs.getString("description"));
        return event;
    };
}
