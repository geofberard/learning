package com.gberard.learning.domain.port.input;

import com.gberard.learning.domain.model.Card;

import java.util.List;

public interface CardService {
    List<Card> findAll();
}
