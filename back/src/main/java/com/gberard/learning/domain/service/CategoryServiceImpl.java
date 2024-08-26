package com.gberard.learning.domain.service;

import com.gberard.learning.domain.model.Category;
import com.gberard.learning.domain.port.input.CategoryService;
import com.gberard.learning.domain.port.output.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> findAll() {
        return repository.readAll();
    }
}
