package com.example.realestate.model.binding;

import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegistrationBindingModel {
    @NotNull
    @Size(min=4, max=20)
    private String firstName;
    @NotNull
    @Size(min=4, max=20)
    private String lastName;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min=4, max=20)
    private String username;
    @NotNull
    @Size(min=4, max=20)
    private String password;
    @NotNull
    @Size(min=4, max=20)
    private String confirmPassword;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
