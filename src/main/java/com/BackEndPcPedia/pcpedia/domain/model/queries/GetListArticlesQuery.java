package com.BackEndPcPedia.pcpedia.domain.model.queries;

public record GetListArticlesQuery(Integer page,
                                   Integer size,
                                   String status,
                                   String category) {
}
