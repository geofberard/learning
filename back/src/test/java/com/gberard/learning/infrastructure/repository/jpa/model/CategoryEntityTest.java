package com.gberard.learning.infrastructure.repository.jpa.model;

import com.gberard.learning.domain.model.Category;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryEntityTest {

    @Test
    public void testToDomain() {
        // Given
        CategoryEntity boxEntity = new CategoryEntity("category_1", "Category");

        // When
        Category box = CategoryEntity.toDomain(boxEntity);

        // Then
        assertThat(box.id()).isEqualTo("category_1");
        assertThat(box.name()).isEqualTo("Category");
    }

    @Test
    public void testToEntity() {
        // Given
        Category box = new Category("category_1", "Category");

        // When
        CategoryEntity boxEntity = CategoryEntity.toEntity(box);

        // Then
        assertThat(boxEntity.getId()).isEqualTo("category_1");
        assertThat(boxEntity.getName()).isEqualTo("Category");
    }

    @Test
    public void testGenerateUUIDOnPersist() {
        // Given
        CategoryEntity boxEntity = new CategoryEntity();

        // When
        boxEntity.generateUUID();

        // Then
        assertThat(boxEntity.getId()).isNotNull();
        assertThat(boxEntity.getId()).startsWith("category_");
    }

}


