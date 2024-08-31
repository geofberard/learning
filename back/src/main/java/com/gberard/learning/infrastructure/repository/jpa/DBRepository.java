package com.gberard.learning.infrastructure.repository.jpa;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DBRepository<D,E> {

    private final JpaRepository<E,String> repository;
    private final Function<D,E> toEntity;
    private final Function<E,D> toDomain;

    public DBRepository(JpaRepository<E, String> repository, Function<D,E> toEntity, Function<E, D> toDomain) {
        this.repository = repository;
        this.toEntity = toEntity;
        this.toDomain = toDomain;
    }

    public List<D> readAll() {
        return repository.findAll().stream().map(toDomain).toList();
    }

    public Optional<D> read(String id) {
        return repository.findById(id).map(toDomain);
    }

    public D readSafe(String id) {
        return repository.findById(id)
                .map(toDomain)
                .orElseThrow(() -> new EntityNotFoundException(getLogName() + " : Unknown id [" + id + "]"));
    }

    private String getLogName() {
        return getClass().getSimpleName().replace("DB", "");
    }

    public D create(D entity) {
        return toDomain.apply(repository.save(toEntity.apply(entity)));
    }

    public D update(D entity) {
        return toDomain.apply(repository.save(toEntity.apply(entity)));
    }

    public void delete(D element) {
        repository.delete(toEntity.apply(element));
    }

}
