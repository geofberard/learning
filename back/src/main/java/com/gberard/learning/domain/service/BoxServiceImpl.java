package com.gberard.learning.domain.service;

import com.gberard.learning.domain.model.Box;
import com.gberard.learning.domain.port.input.BoxService;
import com.gberard.learning.domain.port.output.BoxRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Box> findById(String id) {
        return repository.read(id);
    }

    @Override
    public Box findByIdOrThrow(String id) {
        return repository.readOrThrow(id);
    }

    @Override
    public Box create(String name, int position,int interval) {
        return repository.create(new Box(null, name, position, interval));
    }

    @Override
    public Box update(String id, String name, int position, int interval) {
        Box box = repository.readOrThrow(id);
        return repository.create(new Box(box.id(), name, position, interval));
    }

    @Override
    public boolean delete(String id) {
        Optional<Box> box = findById(id);
        box.ifPresent(repository::delete);
        return box.isPresent();
    }

    @Override
    public Optional<Box> findFirst() {
        return repository.findByPosition(1);
    }

    @Override
    public Optional<Box> findNext(Box box) {
        return repository.findByPosition(box.position() + 1);
    }
}
