package com.BackEndPcPedia.pcpedia.domain.model.commands.handler;

import com.BackEndPcPedia.pcpedia.application.dto.UsersResponse;
import com.BackEndPcPedia.pcpedia.domain.model.aggregates.Users;
import com.BackEndPcPedia.pcpedia.domain.model.commands.UpdatePasswordCommand;
import com.BackEndPcPedia.pcpedia.infrastructure.persistence.jpa.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdatePasswordHandler {
    private final UsersRepository usersRepository;

    public UpdatePasswordHandler(UsersRepository _usersRepository) {
        this.usersRepository = _usersRepository;
    }

    @Transactional
    public UsersResponse handle(UpdatePasswordCommand cmd) {
        var user = usersRepository.findById(cmd.userId()).orElseThrow(()
                -> new IllegalArgumentException("User not found"));

        if(!user.getPasswordHash().getValue().equals(cmd.oldPassword())){
            throw new IllegalArgumentException("Incorrect current password");
        }

        user.changePassword(Users.PasswordHash.of(cmd.newPassword()));
        var saved=usersRepository.save(user);

        return new UsersResponse(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getPhoneNumber(),
                saved.getAddress(),
                saved.getRole().name()
        );
    }
}
