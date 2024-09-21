package com.gberard.learning.application.controller;

import com.gberard.learning.application.dto.category.CategoryDTO;
import com.gberard.learning.application.dto.category.CreateCategoryDTO;
import com.gberard.learning.application.dto.category.UpdateCategoryDTO;
import com.gberard.learning.domain.model.Category;
import com.gberard.learning.domain.port.input.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllCategories() {
        // Given
        Category category = new Category("1", "Test Category");
        when(categoryService.findAll()).thenReturn(Collections.singletonList(category));

        // When
        ResponseEntity<List<CategoryDTO>> response = categoryController.findBoxes();

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody()).containsExactly(new CategoryDTO("1", "Test Category"));
        verify(categoryService).findAll();
    }

    @Test
    void testCreateCategory() {
        // Given
        CreateCategoryDTO createCategoryDTO = new CreateCategoryDTO("New Category");
        Category newCategory = new Category("2", "New Category");
        when(categoryService.create(createCategoryDTO.name())).thenReturn(newCategory);

        // When
        ResponseEntity<CategoryDTO> response = categoryController.createCategory(createCategoryDTO);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(new CategoryDTO("2", "New Category"));
        verify(categoryService).create(createCategoryDTO.name());
    }

    @Test
    void testFindCategoryByIdFound() {
        // Given
        String id = "1";
        Category category = new Category(id, "Test Category");
        when(categoryService.findById(id)).thenReturn(Optional.of(category));

        // When
        ResponseEntity<CategoryDTO> response = categoryController.findCategorydById(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(new CategoryDTO("1", "Test Category"));
        verify(categoryService).findById(id);
    }

    @Test
    void testFindCategoryByIdNotFound() {
        // Given
        String id = "non-existent-id";
        when(categoryService.findById(id)).thenReturn(Optional.empty());

        // When
        ResponseEntity<CategoryDTO> response = categoryController.findCategorydById(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(categoryService).findById(id);
    }

    @Test
    void testUpdateCategory() {
        // Given
        String id = "1";
        UpdateCategoryDTO updateCategoryDTO = new UpdateCategoryDTO("Updated Category");
        Category updatedCategory = new Category(id, "Updated Category");
        when(categoryService.update(id, updateCategoryDTO.name())).thenReturn(updatedCategory);

        // When
        ResponseEntity<CategoryDTO> response = categoryController.updateCategory(id, updateCategoryDTO);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(new CategoryDTO("1", "Updated Category"));
        verify(categoryService).update(id, updateCategoryDTO.name());
    }

    @Test
    void testDeleteCategorySuccess() {
        // Given
        String id = "1";
        when(categoryService.delete(id)).thenReturn(true);

        // When
        ResponseEntity<Void> response = categoryController.deleteCategory(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(categoryService).delete(id);
    }

    @Test
    void testDeleteCategoryNotFound() {
        // Given
        String id = "non-existent-id";
        when(categoryService.delete(id)).thenReturn(false);

        // When
        ResponseEntity<Void> response = categoryController.deleteCategory(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(categoryService).delete(id);
    }
}
