package com.gberard.learning.application.dto;

import com.gberard.learning.domain.model.Category;

public record CategoryDTO(String id, String name) {

    public static CategoryDTO toDTO(Category category) {
        return new CategoryDTO(
                category.id(),
                category.name()
        );
    }

}
