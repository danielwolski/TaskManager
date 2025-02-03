package com.calendarapp.repository;

import com.calendarapp.model.Event;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Event getById(Long id);

    List<Event> getAllByStartTime(LocalDate date);

    List<Event> findAll();
}