package com.calendarapp.repository;

import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.calendarapp.exception.TaskNotFoundException;
import com.calendarapp.model.Task;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public Task save(Task task) {
        String sql = "INSERT INTO tasks (done, description) VALUES (?, ?)";
        try {
            jdbcTemplate.update(sql, task.isDone(), task.getDescription());
            log.info("Task saved successfully: {}", task);
            return task;
        } catch (Exception e) {
            log.error("Error saving task: {}", task, e);
            throw new RuntimeException("Failed to save task", e);
        }
    }

    public List<Task> getAll() {
        String sql = "SELECT * FROM tasks ORDER BY id ASC";
        try {
            return jdbcTemplate.query(sql, taskRowMapper);
        } catch (Exception e) {
            log.error("Error fetching all tasks", e);
            return Collections.emptyList();
        }
    }

    @Transactional
    public void deleteById(Long id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected == 0) {
                throw new TaskNotFoundException("No task found with id: " + id);
            }
            log.info("Task with id {} successfully deleted.", id);
        } catch (Exception e) {
            log.error("Error deleting task with id: {}", id, e);
            throw new RuntimeException("Failed to delete task", e);
        }
    }

    @Transactional
    public void toggleIsDone(Long id) {
        try {
            Boolean isDone = fetchTaskDoneStatusById(id);
            if (isDone == null) {
                log.warn("Task with id {} not found.", id);
                return;
            }

            String updateSql = "UPDATE tasks SET done = ? WHERE id = ?";
            jdbcTemplate.update(updateSql, !isDone, id);

            log.info("Task with id {} successfully toggled. New done status: {}", id, !isDone);
        } catch (Exception e) {
            log.error("Error toggling done status for task with id: {}", id, e);
            throw new RuntimeException("Failed to toggle task done status", e);
        }
    }

    private Boolean fetchTaskDoneStatusById(Long id) {
        String sql = "SELECT done FROM tasks WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Boolean.class, id);
        } catch (Exception e) {
            log.error("Error fetching done status for task with id: {}", id, e);
            throw new RuntimeException("Failed to fetch task status", e);
        }
    }

    private final RowMapper<Task> taskRowMapper = (rs, rowNum) -> {
        Task task = new Task();
        task.setId(rs.getLong("id"));
        task.setDone(rs.getBoolean("done"));
        task.setDescription(rs.getString("description"));
        return task;
    };
}
