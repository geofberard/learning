package com.gberard.learning.infrastructure.repository.jpa;

import com.gberard.learning.domain.model.Category;
import com.gberard.learning.domain.port.output.CategoryRepository;
import com.gberard.learning.infrastructure.repository.jpa.model.CategoryEntity;
import org.springframework.stereotype.Repository;

@Repository
public class DBCategoryRepository extends DBRepository<Category, CategoryEntity> implements CategoryRepository {

    public DBCategoryRepository(JpaCategoryRepository repository) {
        super(repository, CategoryEntity::toEntity, CategoryEntity::toDomain);
    }

}
