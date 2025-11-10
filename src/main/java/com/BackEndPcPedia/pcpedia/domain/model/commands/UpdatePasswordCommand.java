package com.BackEndPcPedia.pcpedia.domain.model.commands;

public record UpdatePasswordCommand(Long userId,
                                    String oldPassword,
                                    String newPassword) {
}
