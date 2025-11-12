package com.BackEndPcPedia.pcpedia.domain.model.commands.handler;

import com.BackEndPcPedia.pcpedia.application.dto.ArticlesResponse;
import com.BackEndPcPedia.pcpedia.domain.model.commands.UpdateArticleCommand;
import com.BackEndPcPedia.pcpedia.infrastructure.persistence.jpa.ArticlesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateArticleHandler {
    private final ArticlesRepository repo;
    public UpdateArticleHandler(ArticlesRepository _articlesRepository) {
        this.repo = _articlesRepository;
    }

    @Transactional
    public ArticlesResponse handle(UpdateArticleCommand cmd){
        var a = repo.findById(cmd.id()).orElseThrow(()->
                new IllegalArgumentException("Article don't found"));
        a.update(cmd.name(), cmd.description(), cmd.category(), cmd.price());
        return RegisterArticleHandler.toResponse(a);
    }
}
