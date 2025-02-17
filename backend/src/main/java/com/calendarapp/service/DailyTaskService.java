package com.calendarapp.service;

import java.util.List;

import com.calendarapp.mapper.DailyTaskMapper;
import com.calendarapp.repository.DailyTaskRepository;
import com.calendarapp.rest.dailytask.RestDailyTask;
import org.springframework.stereotype.Service;

import com.calendarapp.model.DailyTask;
import com.calendarapp.rest.dailytask.RestCreateDailyTask;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class DailyTaskService {
    private final DailyTaskMapper dailyTaskMapper;
    private final DailyTaskRepository dailyTaskRepository;

    public DailyTask createDailyTask(RestCreateDailyTask restDailyTask) {
        DailyTask dailyTask = dailyTaskMapper.restCreateDailyTaskToDailyTask(restDailyTask);
        DailyTask savedDailyTask = dailyTaskRepository.save(dailyTask);
        log.info("Daily task saved: {}", savedDailyTask);
        return savedDailyTask;
    }

    public List<RestDailyTask> getAllTasks() {
        return dailyTaskMapper.dailyTaskListToDailyTaskRestList(dailyTaskRepository.findAll());
    }

    public void deleteTask(Long id) {
        dailyTaskRepository.deleteById(id);
    }

    public void toggleIsDone(Long id) {
        dailyTaskRepository.toggleIsDone(id);
    }
}
