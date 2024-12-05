package com.calendarapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.calendarapp.model.Event;
import com.calendarapp.rest.RestEvent;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "id", ignore = true)
    Event eventRestToEvent(RestEvent eventRest);

    RestEvent eventToEventRest(Event event);
}
