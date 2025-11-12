package com.BackEndPcPedia.pcpedia.domain.model.commands;

public record UpdateUserPasswordCommand(Long userId,
                                        String oldPassword,
                                        String newPassword) {
}
