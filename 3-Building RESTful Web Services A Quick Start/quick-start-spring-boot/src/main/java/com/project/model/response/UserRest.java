package com.project.model.response;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRest {

    @NotNull(message = "Firstname cannot be null")
    @Size(min = 2, message = "Firstname must not be less than 2 characters")
    private String firstName;

    @NotNull(message = "Lastname cannot be null")
    @Size(min = 2, message = "Lastname must not be less than 2 characters")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Email
    private String email;

    @NotNull(message = "User ID cannot be null")
    @Size(min = 8, max = 16, message = "User must be equal or greater than 8 characters and less than 16 characters")
    private String userId;

    private String recordId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
}
