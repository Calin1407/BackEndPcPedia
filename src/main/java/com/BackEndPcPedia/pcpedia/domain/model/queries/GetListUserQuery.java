package com.BackEndPcPedia.pcpedia.domain.model.queries;

public record GetListUserQuery(int page, int size) {
    public GetListUserQuery() { this(0,10); }
}
