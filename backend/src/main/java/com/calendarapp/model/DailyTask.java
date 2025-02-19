package com.calendarapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.PrePersist;

import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "dailyTasks")
public class DailyTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean done;
    private String description;
    private LocalDate currentDay = LocalDate.now();

    @PrePersist
    protected void onCreate() {
        if (currentDay == null) {
            currentDay = LocalDate.now();
        }
    }
}
