package com.BackEndPcPedia.pcpedia.domain.model.queries.Handler;

import com.BackEndPcPedia.pcpedia.application.dto.UsersResponse;
import com.BackEndPcPedia.pcpedia.domain.model.queries.GetListUserQuery;
import com.BackEndPcPedia.pcpedia.infrastructure.persistence.jpa.UsersRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetListUserHandler {
    private final UsersRepository repo;

    public GetListUserHandler(UsersRepository _repo) {
        this.repo = _repo;
    }

    @Transactional(readOnly = true)
    public Iterable<UsersResponse> handle(GetListUserQuery q){
        var page = repo.findAll(PageRequest.of(q.page(), q.size()));
        return page.map(u-> new UsersResponse(
                u.getId(),
                u.getName(),
                u.getEmail(),
                u.getPhoneNumber(),
                u.getAddress(),
                u.getRole().name()
        ));
    }
}
