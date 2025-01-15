package com.calendarapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import io.micrometer.common.lang.Nullable;

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
    @Nullable
    private LocalDateTime endTime;
    @Nullable
    private String description;
}
