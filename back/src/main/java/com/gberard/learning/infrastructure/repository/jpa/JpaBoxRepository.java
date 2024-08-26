package com.gberard.learning.infrastructure.repository.jpa;

import com.gberard.learning.infrastructure.repository.jpa.model.BoxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBoxRepository extends JpaRepository<BoxEntity, String> {
}
