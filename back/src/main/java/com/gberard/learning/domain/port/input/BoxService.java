package com.gberard.learning.domain.port.input;

import com.gberard.learning.domain.model.Box;

import java.util.List;
import java.util.Optional;

public interface BoxService {

    List<Box> findAll();

    Optional<Box> findById(String id);

    Box findByIdOrThrow(String id);

    Box create(String name, int position, int interval);

    Box update(String id, String name, int position, int interval);

    boolean delete(String id);

    Optional<Box> findFirst();

    Optional<Box> findNext(Box box);

}
