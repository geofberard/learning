package com.gberard.learning.domain.port.input;

import com.gberard.learning.domain.model.Box;

import java.util.List;
import java.util.Optional;

public interface BoxService {

    List<Box> findAll();

    Optional<Box> findById(String id);

}
