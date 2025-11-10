package com.BackEndPcPedia.pcpedia.domain.model.commands.handler;

import com.BackEndPcPedia.pcpedia.application.dto.UsersResponse;
import com.BackEndPcPedia.pcpedia.domain.model.aggregates.Users;
import com.BackEndPcPedia.pcpedia.domain.model.commands.RegisterUserCommand;
import com.BackEndPcPedia.pcpedia.infrastructure.persistence.jpa.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterUserHandler {
    private final UsersRepository repo;

    public RegisterUserHandler(UsersRepository _repo) {
        this.repo = _repo;
    }

    @Transactional
    public UsersResponse handle(RegisterUserCommand _registerUser) {
        repo.findByEmailValue(_registerUser.email()).ifPresent(user -> {
            throw new IllegalArgumentException("Username already exists");
        });

        var role = toRole(_registerUser.role());
        var user = Users.register(
                _registerUser.name(),
                _registerUser.address(),
                Users.PasswordHash.of(_registerUser.password()),
                _registerUser.phoneNumber(),
                _registerUser.address() ,
                role
        );

        var saved = repo.save(user);
        return toResponse(saved);
    }

    private Users.Role toRole(String role) {
        if (role == null || role.isEmpty()) {
            return Users.Role.CLIENT;
        }
        return Users.Role.valueOf(role.trim().toUpperCase());
    }

    private UsersResponse toResponse(Users u) {
        return new UsersResponse(
                u.getId(),
                u.getName(),
                u.getEmail(),
                u.getPhoneNumber(),
                u.getAddress(),
                u.getRole().name()
        );
    }
}
