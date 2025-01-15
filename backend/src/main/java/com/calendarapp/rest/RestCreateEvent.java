package com.calendarapp.rest;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestCreateEvent {
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
}
