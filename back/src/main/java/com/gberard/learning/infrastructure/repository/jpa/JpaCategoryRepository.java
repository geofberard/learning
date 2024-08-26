package com.gberard.learning.infrastructure.repository.jpa;

import com.gberard.learning.infrastructure.repository.jpa.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, String> {
}
