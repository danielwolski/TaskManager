package com.calendarapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.calendarapp.mapper.TaskMapper;
import com.calendarapp.model.Task;
import com.calendarapp.repository.TaskRepository;
import com.calendarapp.rest.RestCreateTask;

import com.calendarapp.rest.RestTask;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class TaskService {
    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    public Task createTask(RestCreateTask restTask) {
        Task task = taskMapper.restCreateTaskToTask(restTask);
        Task savedTask = taskRepository.save(task);
        log.info("Task saved: {}", savedTask);
        return savedTask;
    }

    public List<RestTask> getAllTasks() {
        return taskMapper.taskListToTaskRestList(taskRepository.getAll());
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void toggleIsDone(Long id) {
        taskRepository.toggleIsDone(id);
    }
}
