package com.gberard.learning.application.controller;

import com.gberard.learning.application.dto.category.CategoryDTO;
import com.gberard.learning.application.dto.category.CreateCategoryDTO;
import com.gberard.learning.application.dto.category.UpdateCategoryDTO;
import com.gberard.learning.domain.model.Category;
import com.gberard.learning.domain.port.input.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findBoxes() {
        List<CategoryDTO> categories = categoryService.findAll().stream()
                .map(CategoryDTO::toDTO)
                .toList();

        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CreateCategoryDTO createCategoryDTO) {
        Category newCategory = categoryService.create(createCategoryDTO.name());
        return ResponseEntity.ok(CategoryDTO.toDTO(newCategory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findCategorydById(@PathVariable String id) {
        return categoryService.findById(id)
                .map(CategoryDTO::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable String id, @RequestBody UpdateCategoryDTO updateCategoryDTO) {
        Category updatedCategory = categoryService.update(id, updateCategoryDTO.name());
        return ResponseEntity.ok(CategoryDTO.toDTO(updatedCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        return categoryService.delete(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
