package com.BackEndPcPedia.pcpedia.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.BackEndPcPedia.pcpedia.domain.model.aggregates.Users;

import java.util.Optional;

/**
 * JPA repository for Users entity
 * @summary
 * This interface allows us to handle SQL with JAVA
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    /**
     * Find all User by Email
     * @param email - email associated with User
     * @return a User entity
     */
    Optional<Users> findAllByEmailValue(String email);

    /**
     * Check if any User is associated with the email
     * @param email - email associated with User
     * @return true if any User is associated with the email, false otherwise
     */
    boolean existsByEmailValue(String email);
}
