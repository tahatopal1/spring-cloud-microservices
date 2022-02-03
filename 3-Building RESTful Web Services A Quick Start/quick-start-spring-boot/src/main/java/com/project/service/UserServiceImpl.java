package com.project.service;

import com.project.model.response.UserRest;
import com.project.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    Map<String, UserRest> users; // Mock database
    Utils utils;

    public UserServiceImpl() { }

    @Autowired // Constructor injection
    public UserServiceImpl(Utils utils) {
        this.utils = utils;
    }

    @Override
    public UserRest createUser(UserRest userRest) {
        if (Objects.isNull(users)) users = new HashMap<>();
        String recordId = utils.generateUserId();
        userRest.setRecordId(recordId);
        users.put(recordId, userRest);
        return userRest;
    }
}
