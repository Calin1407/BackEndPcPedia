package com.BackEndPcPedia.pcpedia.domain.model.queries.Handler;

import com.BackEndPcPedia.pcpedia.application.dto.UsersResponse;
import com.BackEndPcPedia.pcpedia.domain.model.aggregates.Users;
import com.BackEndPcPedia.pcpedia.domain.model.queries.GetUserByIdQuery;
import com.BackEndPcPedia.pcpedia.infrastructure.persistence.jpa.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetUserByIdHandler {
    private final UsersRepository repo;

    public GetUserByIdHandler(UsersRepository _repo) {
        this.repo = _repo;
    }

    @Transactional(readOnly = true)
    public UsersResponse handle(GetUserByIdQuery q) {
        var u = repo.findById(q.id()).orElseThrow(()->
                new IllegalArgumentException("User not found"));
        return toResponse(u);
    }

    private UsersResponse toResponse(Users u){
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
