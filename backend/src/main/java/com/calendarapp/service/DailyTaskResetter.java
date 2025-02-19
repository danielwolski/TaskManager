package com.calendarapp.service;

import com.calendarapp.repository.DailyTaskRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@AllArgsConstructor
@Service
public class DailyTaskResetter {

    DailyTaskRepository dailyTaskRepository;

    @PostConstruct
    public void resetAtTheStartIfNecessary() {
        LocalDate today = LocalDate.now();
        log.info("Resetting daily tasks if necessary");

        log.info("today: {}", today);
        log.info("existing: {}", dailyTaskRepository.findAll());
        dailyTaskRepository.setIsDoneToFalseIfNecessary(today);
    }

    @Scheduled(cron = "59 59 23 * * *")
    public void resetDailyTasksOnNextDay() {
        LocalDateTime now = LocalDateTime.now();
        log.info("Resetting daily tasks: " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        dailyTaskRepository.setAllIsDoneToFalse();
    }
}
