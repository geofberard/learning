package com.gberard.learning.domain.port.input;

import com.gberard.learning.domain.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
}
