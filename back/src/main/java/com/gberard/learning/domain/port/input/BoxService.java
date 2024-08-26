package com.gberard.learning.domain.port.input;

import com.gberard.learning.domain.model.Box;

import java.util.List;

public interface BoxService {
    List<Box> findAll();
}
