package com.gberard.learning.infrastructure.repository.jpa.model;

import com.gberard.learning.domain.model.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity(name = "category")
@Table(name = "categories")
public class CategoryEntity {

    @Id
    private String id;

    private String name;

    @PrePersist
    public void generateUUID() {
        if (this.id == null) {
            this.id = "category_" + UUID.randomUUID();
        }
    }

    public static Category toDomain(CategoryEntity categoryEntity) {
        return new Category(categoryEntity.id, categoryEntity.name);
    }

    public static CategoryEntity toEntity(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.id= category.id();
        categoryEntity.name= category.name();
        return categoryEntity;
    }

}
