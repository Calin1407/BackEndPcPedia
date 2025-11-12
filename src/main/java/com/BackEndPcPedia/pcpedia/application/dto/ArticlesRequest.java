package com.BackEndPcPedia.pcpedia.application.dto;

import java.math.BigDecimal;

public record ArticlesRequest(
        String name,
        String description,
        String category,
        BigDecimal price) {
}
