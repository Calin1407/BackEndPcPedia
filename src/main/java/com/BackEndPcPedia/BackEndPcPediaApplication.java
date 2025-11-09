package com.BackEndPcPedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;

@EnableJpaAuditing
@SpringBootApplication
@EntityScan(basePackages = {
        "com.BackEndPcPedia.pcpedia.domain.model.aggregates"
})
@EnableJpaRepositories(basePackages = {
        "com.BackEndPcPedia.pcpedia.infrastructure.persistence.jpa"
})
@ComponentScan(basePackages = {
        "com.BackEndPcPedia",
        "pcpedia",
        "com.catchupplatform.shared"
})
public class BackEndPcPediaApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackEndPcPediaApplication.class, args);
    }
}