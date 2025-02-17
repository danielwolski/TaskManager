package com.calendarapp.mapper;

import java.util.List;

import com.calendarapp.model.DailyTask;
import com.calendarapp.rest.dailytask.RestCreateDailyTask;
import com.calendarapp.rest.dailytask.RestDailyTask;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.calendarapp.model.DailyTask;

@Mapper(componentModel = "spring")
public interface DailyTaskMapper {

    @Mapping(target = "id", ignore = true)
    DailyTask restCreateDailyTaskToDailyTask(RestCreateDailyTask restDailyTask);

    List<RestDailyTask> dailyTaskListToDailyTaskRestList(List<DailyTask> dailyTaskList);
}
