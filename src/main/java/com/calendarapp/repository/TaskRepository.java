package com.calendarapp.repository;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.calendarapp.model.Task;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class TaskRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Task save(Task task) {
        try {
            String sql = "INSERT INTO tasks (done, description) VALUES (?, ?)";
            jdbcTemplate.update(sql, task.isDone(), task.getDescription());
            log.info("Task saved successfully: {}", task);
        } catch (Exception e) {
            log.error("Error saving task: ", e);
        }
        return task;
    }

    public List<Task> getAll() {
        try {
            String sql = "SELECT * FROM tasks ORDER BY id ASC";
            
            return jdbcTemplate.query(sql, (rs, rowNum) -> {
                Task task = new Task();
                task.setId(rs.getLong("id"));
                task.setDone(rs.getBoolean("done"));
                task.setDescription(rs.getString("description"));
                return task;
            });
        } catch (Exception e) {
            log.error("Error getting all tasks: ", e);
            return Collections.emptyList();
        }
    }

    public void deleteById(Long id) {
        try {
            String sql = "DELETE FROM tasks WHERE id = ?";
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected == 0) {
                log.warn("No task found with id: " + id);
                //TODO Use GlobalExceptionHandler when throwing exceptions
            } else {
                log.info("Task with id " + id + " successfully deleted.");
            }
        } catch (Exception e) {
            log.error("Error deleting task with id: " + id, e);
        }
    }

    public void toggleIsDone(Long id) {
        try {
            String fetchSql = "SELECT done FROM tasks WHERE id = ?";
            Boolean isDone = jdbcTemplate.queryForObject(fetchSql, Boolean.class, id);
    
            if (isDone == null) {
                log.warn("Task with id {} not found.", id);
                return;
            }
    
            String updateSql = "UPDATE tasks SET done = ? WHERE id = ?";
            jdbcTemplate.update(updateSql, !isDone, id);
    
            log.info("Task with id {} successfully toggled. New done: {}", id, !isDone);
        } catch (Exception e) {
            log.error("Error toggling done for task with id: " + id, e);
        }
    }
    
}
