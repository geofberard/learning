package com.gberard.learning.domain.service;

import com.gberard.learning.domain.model.Category;
import com.gberard.learning.domain.port.input.CategoryService;
import com.gberard.learning.domain.port.output.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Category> findById(String id) {
        return repository.read(id);
    }

    @Override
    public Category create(String name) {
        return repository.create(new Category(null, name));
    }

    @Override
    public Category update(String id, String name) {
        Category category = repository.readSafe(id);
        return repository.update(new Category(category.id(), name));
    }

    @Override
    public boolean delete(String id) {
        Optional<Category> box = findById(id);
        box.ifPresent(repository::delete);
        return box.isPresent();
    }

}
