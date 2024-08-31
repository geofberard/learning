package com.gberard.learning.domain.port.input;

import com.gberard.learning.domain.model.Card;

import java.util.List;
import java.util.Optional;

public interface CardService {

    List<Card> findAll();

    Optional<Card> findById(String id);

    boolean delete(String id);

}
