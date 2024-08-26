package com.gberard.learning.application.controller;

import com.gberard.learning.application.dto.BoxDTO;
import com.gberard.learning.application.dto.CategoryDTO;
import com.gberard.learning.domain.port.input.BoxService;
import com.gberard.learning.domain.port.input.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
