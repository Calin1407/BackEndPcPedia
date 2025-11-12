package com.BackEndPcPedia.pcpedia.infrastructure.persistence.jpa;

import com.BackEndPcPedia.pcpedia.domain.model.aggregates.Articles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticlesRepository extends JpaRepository<Articles, Long> {
    /**
     * Method to search all Articles with same category
     * @param _status status to search
     * @param _pageable allows controlled data return
     * @return Articles in database associated with status
     */
    Page<Articles> findByStatus(Articles.Status _status, Pageable _pageable);

    /**
     * Method to search all Articles in the same category
     * @param _category category to search
     * @param _pageable allows controlled data return
     * @return Articles in database associated with categories
     */
    Page<Articles> findByCategory(String _category, Pageable _pageable);
}
