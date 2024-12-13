package com.calendarapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table("tasks")
public class Task {
    @Id
    private Long id;
    private boolean done;
    private String description;
}
