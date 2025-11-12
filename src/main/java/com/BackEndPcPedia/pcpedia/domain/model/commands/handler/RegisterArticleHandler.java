package com.BackEndPcPedia.pcpedia.domain.model.commands.handler;

import com.BackEndPcPedia.pcpedia.application.dto.ArticlesResponse;
import com.BackEndPcPedia.pcpedia.domain.model.aggregates.Articles;
import com.BackEndPcPedia.pcpedia.domain.model.commands.RegisterArticleCommand;
import com.BackEndPcPedia.pcpedia.infrastructure.persistence.jpa.ArticlesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterArticleHandler {

    private final ArticlesRepository articlesRepository;
    public RegisterArticleHandler(ArticlesRepository _articlesRepository) {
        this.articlesRepository = _articlesRepository;
    }

    @Transactional
    public ArticlesResponse handle(RegisterArticleCommand cmd) {
        var article = Articles.create(cmd.name()
                ,cmd.description(),cmd.category(),cmd.price());
        var saved = articlesRepository.save(article);

        return toResponse(saved);
    }

    public static ArticlesResponse toResponse(Articles _article) {
        return new ArticlesResponse(
                _article.getId(),_article.getName(),_article.getDescription(),
                _article.getCategory(),_article.getPrice(),_article.getStatus().name()
        );
    }
}