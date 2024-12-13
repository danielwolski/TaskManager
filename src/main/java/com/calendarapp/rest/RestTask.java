package com.calendarapp.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestTask {
    private Long id;
    private boolean done;
    private String description;
}
