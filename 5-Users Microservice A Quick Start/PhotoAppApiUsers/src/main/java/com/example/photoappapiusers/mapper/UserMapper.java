package com.example.photoappapiusers.mapper;

import com.example.photoappapiusers.dto.UserDto;
import com.example.photoappapiusers.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User convertUserDtoToUser(UserDto userDto){
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        return user;
    }

}
