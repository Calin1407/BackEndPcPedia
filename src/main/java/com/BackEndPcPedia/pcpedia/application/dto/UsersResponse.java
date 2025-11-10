package com.BackEndPcPedia.pcpedia.application;

/**
 * We cannot connect directly to the database
 * For security reasons, it is recommended to create
 * an API to connect to the database
 * This UserResponse show how this API responds
 * @param id is a Long associated with a User
 * @param name is a User's name
 * @param email is the email associated with User
 * @param phoneNumber is the User's phone number
 * @param address is where live User
 * @param role is the User's role
 */
public record UsersResponse(Long id,
                            String name,
                            String email,
                            String phoneNumber,
                            String address,
                            String role) {
}
