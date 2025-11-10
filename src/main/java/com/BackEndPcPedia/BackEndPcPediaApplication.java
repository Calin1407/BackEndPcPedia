package com.BackEndPcPedia;


import com.BackEndPcPedia.pcpedia.infrastructure.persistence.jpa.UsersRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;

//Test
import com.BackEndPcPedia.pcpedia.domain.model.aggregates.Users;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

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

    /**
     * This is only for test User table
     * @param repo fake User
     */
    @Profile("dev")
    @Bean
    CommandLineRunner initUsers(UsersRepository repo) {
        return args -> {
            repo.findByEmailValue("demo@pcpedia.com")
                    .orElseGet(() -> repo.save(
                            Users.register(
                                    "Demo",
                                    "demo@pcpedia.com",
                                    Users.PasswordHash.of("0123456789abcdef0123"),
                                    "999999999",
                                    "Lima, PE",
                                    Users.Role.CLIENT
                            )
                    ));
        };
    }
}