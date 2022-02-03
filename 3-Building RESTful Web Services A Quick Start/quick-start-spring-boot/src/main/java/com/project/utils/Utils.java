package com.project.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Utils {

    public String generateUserId(){
        return UUID.randomUUID().toString();
    }

}
