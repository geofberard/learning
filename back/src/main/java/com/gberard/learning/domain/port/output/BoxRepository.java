package com.gberard.learning.domain.port.output;

import com.gberard.learning.domain.model.Box;

import java.util.Optional;

public interface BoxRepository extends DataRepository<Box> {

    Optional<Box> findByPosition(int position);

}
