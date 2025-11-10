package com.BackEndPcPedia.pcpedia.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * The Users can't connect with database
 * This record interactive with Users to send them information
 * @param name is the User's name
 * @param email is the email associated with User
 * @param password is the string of the User's password
 * @param phoneNumber is the User's phone number
 * @param address is where live User
 * @param role is the User's role
 */
public record UsersRequest(
        @NotBlank @Size(max=100) String name,
        @NotBlank @Email @Size(max=100) String email,
        @NotBlank @Size(min=6, max=100) String password,
        @NotBlank @Size(max=20) String phoneNumber,
        @NotBlank @Size(max=200) String address,
        String role) {
}