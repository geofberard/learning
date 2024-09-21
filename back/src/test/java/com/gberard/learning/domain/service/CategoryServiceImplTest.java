package com.gberard.learning.domain.service;

import com.gberard.learning.domain.model.Category;
import com.gberard.learning.domain.port.output.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindAllCategories() {
        // Given
        Category category = new Category("1", "Science");
        when(categoryRepository.readAll()).thenReturn(List.of(category));

        // When
        List<Category> categories = categoryService.findAll();

        // Then
        assertThat(categories).containsExactly(category);
        verify(categoryRepository).readAll();
    }

    @Test
    void shouldFindCategoryById() {
        // Given
        Category category = new Category("1", "Science");
        when(categoryRepository.read("1")).thenReturn(Optional.of(category));

        // When
        Optional<Category> result = categoryService.findById("1");

        // Then
        assertThat(result).contains(category);
        verify(categoryRepository).read("1");
    }

    @Test
    void shouldCreateCategory() {
        // Given
        Category category = new Category(null, "Science");
        when(categoryRepository.create(any(Category.class))).thenReturn(new Category("1", "Science"));

        // When
        Category createdCategory = categoryService.create("Science");

        // Then
        assertThat(createdCategory).isNotNull();
        assertThat(createdCategory.id()).isEqualTo("1");
        assertThat(createdCategory.name()).isEqualTo("Science");
        verify(categoryRepository).create(any(Category.class));
    }

    @Test
    void shouldUpdateCategory() {
        // Given
        Category category = new Category("1", "Old Name");
        when(categoryRepository.readOrThrow("1")).thenReturn(category);
        when(categoryRepository.update(any(Category.class))).thenReturn(new Category("1", "New Name"));

        // When
        Category updatedCategory = categoryService.update("1", "New Name");

        // Then
        assertThat(updatedCategory.name()).isEqualTo("New Name");
        verify(categoryRepository).readOrThrow("1");
        verify(categoryRepository).update(any(Category.class));
    }

    @Test
    void shouldDeleteCategory() {
        // Given
        Category category = new Category("1", "Science");
        when(categoryRepository.read("1")).thenReturn(Optional.of(category));

        // When
        boolean result = categoryService.delete("1");

        // Then
        assertThat(result).isTrue();
        verify(categoryRepository).read("1");
        verify(categoryRepository).delete(category);
    }

}
