package com.BackEndPcPedia.pcpedia.domain.model.commands;

import java.math.BigDecimal;

public record UpdateArticleCommand(Long id,
                                   String name,
                                   String description,
                                   String category,
                                   BigDecimal price) {
}
