package com.hallyugo.hallyugo.content.repository;

import com.hallyugo.hallyugo.content.domain.Category;
import com.hallyugo.hallyugo.content.domain.Content;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {

    @Query("SELECT c FROM Content c WHERE c.category = :category ORDER BY RAND()")
    List<Content> findRandomContentsByCategory(@Param("category") Category category, Pageable pageable);

    List<Content> findByCategory(Category category);
}