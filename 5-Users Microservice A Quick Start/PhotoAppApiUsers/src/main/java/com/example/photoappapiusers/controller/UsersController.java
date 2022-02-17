package com.example.photoappapiusers.controller;

import com.example.photoappapiusers.dto.UserDto;
import com.example.photoappapiusers.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private Environment env;

    @Autowired
    private UsersService usersService;

    @GetMapping("/status/check")
    public String status(){
        return "Working on port " + env.getProperty("local.server.port")
                + "with token = " + env.getProperty("token.secret");
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        usersService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
}
