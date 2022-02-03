package com.project.model.response;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Created to sustain the validation with only using firstname and lastname
public class UpdateUserRest {

    @NotNull(message = "Firstname cannot be null")
    @Size(min = 2, message = "Firstname must not be less than 2 characters")
    private String firstName;

    @NotNull(message = "Lastname cannot be null")
    @Size(min = 2, message = "Lastname must not be less than 2 characters")
    private String lastName;

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
}
