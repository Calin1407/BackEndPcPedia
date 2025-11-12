package com.BackEndPcPedia.pcpedia.domain.model.queries.Handler;

import com.BackEndPcPedia.pcpedia.application.dto.ArticlesResponse;
import com.BackEndPcPedia.pcpedia.domain.model.aggregates.Articles;
import com.BackEndPcPedia.pcpedia.domain.model.commands.handler.RegisterArticleHandler;
import com.BackEndPcPedia.pcpedia.domain.model.queries.GetListArticlesQuery;
import com.BackEndPcPedia.pcpedia.infrastructure.persistence.jpa.ArticlesRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetListArticleHandler {
    private final ArticlesRepository repo;
    public GetListArticleHandler(ArticlesRepository _repo) {
        this.repo = _repo;
    }

    @Transactional(readOnly = true)
    public Iterable<ArticlesResponse> handle(GetListArticlesQuery query) {
        var pageable = PageRequest.of(query.page()==null? 0: query.page(),
                query.size()==null? 10: query.size());

        var page = (query.status()!=null && !query.status().isBlank())?
                repo.findByStatus(Articles.Status.valueOf(query.status().trim().toUpperCase()), pageable)
                : (query.category()!=null && !query.category().isBlank())?
                    repo.findByCategory(query.category().trim(), pageable)
                    : repo.findAll(pageable);

        return page.map(RegisterArticleHandler::toResponse);
    }
}
