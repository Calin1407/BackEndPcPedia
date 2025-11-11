package com.BackEndPcPedia.pcpedia.application.dto;

import java.math.BigDecimal;

public record ArticlesResponse(
        Long _id,
        String _name,
        String _description,
        String _category,
        BigDecimal _price,
        String _state) {
}
