package com.BackEndPcPedia.pcpedia.domain.model.queries.Handler;

import com.BackEndPcPedia.pcpedia.application.dto.ArticlesResponse;
import com.BackEndPcPedia.pcpedia.domain.model.commands.handler.RegisterArticleHandler;
import com.BackEndPcPedia.pcpedia.domain.model.queries.GetArticleByIdQuery;
import com.BackEndPcPedia.pcpedia.infrastructure.persistence.jpa.ArticlesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetArticleByIdHandler {
    private final ArticlesRepository repo;
    public GetArticleByIdHandler(ArticlesRepository _repo) {
        this.repo = _repo;
    }

    @Transactional(readOnly = true)
    public ArticlesResponse handle(GetArticleByIdQuery q) {
        var a = repo.findById(q.id()).orElseThrow(()->
                new IllegalArgumentException("Article don't found"));
        return RegisterArticleHandler.toResponse(a);
    }
}