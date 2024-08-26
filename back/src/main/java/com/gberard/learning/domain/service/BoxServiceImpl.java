package com.gberard.learning.domain.service;

import com.gberard.learning.domain.model.Box;
import com.gberard.learning.domain.port.input.BoxService;
import com.gberard.learning.domain.port.output.BoxRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxServiceImpl implements BoxService {

    private final BoxRepository repository;

    public BoxServiceImpl(BoxRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Box> findAll() {
        return repository.readAll();
    }
}
