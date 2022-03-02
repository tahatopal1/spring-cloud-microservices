package com.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file-config")
public class GeneralController {

    @GetMapping("/home")
    public String getHome(){
        return "Home";
    }

}
