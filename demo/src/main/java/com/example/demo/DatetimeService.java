package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class DatetimeService {

    public Datetime getDate(){
        return new Datetime();
    }
}
