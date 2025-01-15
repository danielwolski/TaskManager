package com.calendarapp.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.calendarapp.model.Task;
import com.calendarapp.rest.RestCreateTask;
import com.calendarapp.rest.RestTask;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    
    @Mapping(target = "id", ignore = true)
    Task restCreateTaskToTask(RestCreateTask restTask);

    List<RestTask> taskListToTaskRestList(List<Task> taskList);
}
