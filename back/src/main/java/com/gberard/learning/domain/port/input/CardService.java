package com.gberard.learning.domain.port.input;

import com.gberard.learning.domain.model.Box;
import com.gberard.learning.domain.model.Card;
import com.gberard.learning.domain.model.Category;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CardService {

    List<Card> findAll();

    Optional<Card> findById(String id);

    Card create(
            String question,
            String answer,
            String boxId,
            LocalDate lastReviewedDate,
            LocalDate nextReviewDate,
            String categoryId
    );

    Card update(
            String id,
            String question,
            String answer,
            String boxId,
            LocalDate lastReviewedDate,
            LocalDate nextReviewDate,
            String categoryId
    );

    boolean delete(String id);

}
