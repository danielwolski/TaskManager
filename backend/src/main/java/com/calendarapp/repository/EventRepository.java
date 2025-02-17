package com.calendarapp.repository;

import com.calendarapp.model.Event;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Event getById(Long id);

    @Query("SELECT e FROM Event e WHERE e.startTime BETWEEN :startOfDay AND :endOfDay")
    List<Event> findAllByStartTimeBetween(@Param("startOfDay") LocalDateTime startOfDay,
                                          @Param("endOfDay") LocalDateTime endOfDay);

    List<Event> findAll();
}