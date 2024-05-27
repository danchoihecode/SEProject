package com.chattingweb.backend.services.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterUser {
    @NotBlank(message = "Email is required")
    @Size(min = 10, max = 100,message = "Length of email must be between 10 and 100")
    @Email(message = "The email is invalid",flags = {Pattern.Flag.CASE_INSENSITIVE})
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 8,max = 40,message = "Length of password must be between 8 and 40")
    private String password;
    @NotBlank(message = "Name is required")
    @Size(max = 40,message = "Length of name must be under 40")
    private String fullName;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}