package com.gberard.learning.domain.port.output;

import com.gberard.learning.domain.model.Identified;

import java.util.List;
import java.util.Optional;

public interface DataRepository<T extends Identified> {

    List<T> readAll();

    Optional<T> read(String id);

    T readOrThrow(String id);

    T create(T entity);

    T update(T entity);

    void delete(T element);

}
