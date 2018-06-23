package com.hopiestra.site.repository;

import com.hopiestra.site.domain.Article;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the Article entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("select article from Article article where article.author.login = ?#{principal.username}")
    List<Article> findByAuthorIsCurrentUser();

}