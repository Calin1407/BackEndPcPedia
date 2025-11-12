package com.BackEndPcPedia.pcpedia.application.dto;

import com.BackEndPcPedia.pcpedia.domain.model.aggregates.Articles;

import java.math.BigDecimal;

public record ArticlesResponse(
        Long id,
        String name,
        String description,
        String category,
        BigDecimal price,
        String status) {
    public static ArticlesResponse of(Articles articles) {
        return new ArticlesResponse(
                articles.getId(),
                articles.getName(),
                articles.getDescription(),
                articles.getCategory(),
                articles.getPrice(),
                articles.getStatus().name()
        );
    }
}
