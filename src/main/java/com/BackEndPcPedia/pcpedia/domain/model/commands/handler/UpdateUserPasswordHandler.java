package com.BackEndPcPedia.pcpedia.domain.model.commands.handler;

import com.BackEndPcPedia.pcpedia.application.dto.UsersResponse;
import com.BackEndPcPedia.pcpedia.domain.model.commands.UpdateUserPasswordCommand;
import com.BackEndPcPedia.pcpedia.infrastructure.persistence.jpa.UsersRepository;
import com.BackEndPcPedia.shared.domain.valueobject.PasswordHash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateUserPasswordHandler {
    private final UsersRepository usersRepository;

    public UpdateUserPasswordHandler(UsersRepository _usersRepository) {
        this.usersRepository = _usersRepository;
    }

    @Transactional
    public UsersResponse handle(UpdateUserPasswordCommand cmd) {
        var user = usersRepository.findById(cmd.userId()).orElseThrow(()
                -> new IllegalArgumentException("User not found"));

        if(!user.getPasswordHash().getValue().equals(cmd.oldPassword())){
            throw new IllegalArgumentException("Incorrect current password");
        }

        user.changePassword(PasswordHash.of(cmd.newPassword()));
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
