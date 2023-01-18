package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/datetime")
public class DatetimeController {

    private final DatetimeService datetimeService;

    @Autowired
    public DatetimeController(DatetimeService datetimeService) {
        this.datetimeService = datetimeService;
    }

    @GetMapping
    public Datetime getDate(){
       return datetimeService.getDate();
    }
}
