package com.example.photoappapiusers.service;

import com.example.photoappapiusers.client.AlbumServiceClient;
import com.example.photoappapiusers.dto.AlbumDto;
import com.example.photoappapiusers.dto.UserDto;
import com.example.photoappapiusers.mapper.UserMapper;
import com.example.photoappapiusers.modal.User;
import com.example.photoappapiusers.repository.UsersRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @Autowired
    private AlbumServiceClient albumServiceClient;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.convertUserDtoToUser(userDto);
        user.setId(UUID.randomUUID().toString());
        usersRepository.save(user);
        return userDto;
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .convertValue(usersRepository.findByEmail(email), UserDto.class);
    }

    /*@Override
    public UserDto getUserByUserId(String userId) {
        User user = usersRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String albumsUrl = env.getProperty("albums.url");
        String url = String.format(albumsUrl, userId);
        ResponseEntity<List<AlbumDto>> albumsResponse = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<AlbumDto>>() {});
        List<AlbumDto> albums = albumsResponse.getBody();
        UserDto userDto = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .convertValue(user, UserDto.class);
        userDto.setAlbums(albums);
        return userDto;
    }  ** COMMENTED OUT FOR FEIGN CLIENT **  */

    @Override
    public UserDto getUserByUserId(String userId) {
        User user = usersRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        logger.info("Before calling albums microservice...");
        List<AlbumDto> albums = albumServiceClient.getAlbums(userId);
        logger.info("After calling albums microservice...");
        UserDto userDto = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .convertValue(user, UserDto.class);
        userDto.setAlbums(albums);
        return userDto;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findByEmail(username);
        if (user == null) throw new UsernameNotFoundException(username);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true,
                true, true, true, new ArrayList<>());
    }
}
