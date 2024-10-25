package com.hallyugo.hallyugo.content.repository;

import com.hallyugo.hallyugo.content.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
