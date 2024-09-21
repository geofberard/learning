package com.gberard.learning.application.dto.category;

import static org.assertj.core.api.Assertions.assertThat;

import com.gberard.learning.domain.model.Category;
import org.junit.jupiter.api.Test;

public class CategoryDTOTest {

    @Test
    public void testToDTO() {
        // Given
        Category category = new Category("categoryId", "Programming");  // Création d'une catégorie simulée

        // When
        CategoryDTO categoryDTO = CategoryDTO.toDTO(category);

        // Then
        assertThat(categoryDTO.id()).isEqualTo("categoryId");
        assertThat(categoryDTO.name()).isEqualTo("Programming");
    }

}
