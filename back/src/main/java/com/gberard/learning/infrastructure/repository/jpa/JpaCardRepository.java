package com.gberard.learning.infrastructure.repository.jpa;

import com.gberard.learning.infrastructure.repository.jpa.model.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCardRepository extends JpaRepository<CardEntity, String> {
}
