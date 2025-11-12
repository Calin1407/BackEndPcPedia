package com.BackEndPcPedia.pcpedia.domain.model.commands;

public record RegisterUserCommand(
        String name,
        String email,
        String password,
        String phoneNumber,
        String address,
        String role
) {

}
