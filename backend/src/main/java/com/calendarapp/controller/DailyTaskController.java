package com.calendarapp.controller;

import java.util.List;

import com.calendarapp.model.DailyTask;
import com.calendarapp.rest.dailytask.RestCreateDailyTask;
import com.calendarapp.rest.dailytask.RestDailyTask;
import com.calendarapp.service.DailyTaskService;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/daily-tasks")
@AllArgsConstructor
public class DailyTaskController {

    private final DailyTaskService dailyTaskService;

    @PostMapping
    public ResponseEntity<DailyTask> createDailyTask(@RequestBody RestCreateDailyTask restCreateDailyTask) {
        log.info("Received create daily task request {}", restCreateDailyTask.getDescription());
        DailyTask createdDailyTask = dailyTaskService.createDailyTask(restCreateDailyTask);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(createdDailyTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteDailyTask(@PathVariable Long id) {
        log.info("Received delete daily task request {}", id);
        dailyTaskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<RestDailyTask>> getAllDailyTasksForGroup() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dailyTaskService.getAllTasksForGroup());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> toggleIsDone(@PathVariable Long id) {
        log.info("Received toggle done status for daily task {}", id);
        dailyTaskService.toggleIsDone(id);
        return ResponseEntity.ok().build();
    }
}
