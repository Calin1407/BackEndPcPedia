package com.BackEndPcPedia.pcpedia.domain.model.commands;

import java.math.BigDecimal;

public record RegisterArticleCommand(String name,
                                     String description,
                                     String category,
                                     BigDecimal price){
}
