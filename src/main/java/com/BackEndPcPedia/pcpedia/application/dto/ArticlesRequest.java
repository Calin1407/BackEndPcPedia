package com.BackEndPcPedia.pcpedia.application.dto;

import java.math.BigDecimal;

public record ArticlesRequest(
        String _name,
        String _description,
        String _category,
        BigDecimal _price) {
}
