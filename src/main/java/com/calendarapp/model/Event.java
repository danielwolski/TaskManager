package com.calendarapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table("events")
public class Event {
    @Id
    private Long id;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
