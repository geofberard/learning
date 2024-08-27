package com.gberard.learning.infrastructure.repository.jpa;

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

}
