package com.project.controller;

import com.project.exception.UserServiceException;
import com.project.model.response.UpdateUserRest;
import com.project.model.response.UserRest;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    Map<String, UserRest> users; // Mock database

    @Autowired // Injecting
    private UserService userService;

    // We should use 'required = false' when we are using non-primitive types (otherwise we get an error)
    @GetMapping
    public String getUser(@RequestParam(value = "page", required = false) Integer page,
                          @RequestParam(value = "limit", defaultValue = "1") int limit){
        return "get user was called, page: " + page + ", limit: " + limit;
    }

    // We rearrange the method by using Response Entity to be able to choose status code
    @GetMapping(path = "/{userId}",
            produces = {
                    MediaType.APPLICATION_XML_VALUE, // XML producing enabled
                    MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<UserRest> getUserById(@PathVariable String userId){

        /*String firstName = null;
        int length = firstName.length();*/ // This will cause an error (For trying controlling advice)

        if (true) throw new UserServiceException("A user service exception is thrown"); // This will cause an error (For handling custom exception)

        if (users.containsKey(userId))
            return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);
        return new ResponseEntity<UserRest>(HttpStatus.BAD_REQUEST); // Bad request mocking
    }

    // We've set request body to post an object
    @PostMapping(consumes = {
            MediaType.APPLICATION_XML_VALUE, // XML consuming enabled
            MediaType.APPLICATION_JSON_VALUE
    },produces = {
            MediaType.APPLICATION_XML_VALUE, // XML producing enabled
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserRest userRest){ // @Valid is added for validate the object
        UserRest user = userService.createUser(userRest);
        return new ResponseEntity<UserRest>(user, HttpStatus.OK);
    }

    @PutMapping(path = "/{userId}" ,consumes = {
            MediaType.APPLICATION_XML_VALUE, // XML consuming enabled
            MediaType.APPLICATION_JSON_VALUE
    },produces = {
            MediaType.APPLICATION_XML_VALUE, // XML producing enabled
            MediaType.APPLICATION_JSON_VALUE
    })
    public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserRest userRest){
        UserRest storedUserDetails = users.get(userId);
        storedUserDetails.setFirstName(userRest.getFirstName());
        storedUserDetails.setLastName(userRest.getLastName());
        users.put(userId, storedUserDetails);
        return storedUserDetails;
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId){
        users.remove(userId);
        return ResponseEntity.noContent().build();
    }

}
