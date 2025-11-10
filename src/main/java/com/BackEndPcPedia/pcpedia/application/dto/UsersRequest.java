package com.BackEndPcPedia.pcpedia.application;

/**
 * The Users can't connect with the class Users, because security
 * This record interactive with Users to send them information
 * @param name is the User's name
 * @param email is the email associated with User
 * @param password is the string of the User's password
 * @param phoneNumber is the User's phone number
 * @param address is where live User
 * @param role is the User's role
 */
public record UsersRequest(String name,
                           String email,
                           String password,
                           String phoneNumber,
                           String address,
                           String role) {

}
