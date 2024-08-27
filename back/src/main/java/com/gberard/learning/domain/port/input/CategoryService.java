package com.gberard.learning.domain.port.input;

import com.gberard.learning.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();

    Optional<Category> findById(String id);

}
