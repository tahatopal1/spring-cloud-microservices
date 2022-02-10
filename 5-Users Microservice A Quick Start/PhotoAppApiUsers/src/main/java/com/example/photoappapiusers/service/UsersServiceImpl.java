package com.example.photoappapiusers.service;

import com.example.photoappapiusers.dto.UserDto;
import com.example.photoappapiusers.mapper.UserMapper;
import com.example.photoappapiusers.modal.User;
import com.example.photoappapiusers.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.convertUserDtoToUser(userDto);
        user.setId(UUID.randomUUID().toString());
        usersRepository.save(user);
        return userDto;
    }
}
