package com.gberard.learning.domain.port.output;

import com.gberard.learning.domain.model.Identified;

import java.util.List;

public interface DataRepository<T extends Identified> {
    List<T> readAll();
}
