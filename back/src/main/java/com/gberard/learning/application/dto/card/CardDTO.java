package com.gberard.learning.application.dto.card;

import com.gberard.learning.application.dto.box.BoxDTO;
import com.gberard.learning.application.dto.category.CategoryDTO;
import com.gberard.learning.domain.model.Card;

import java.time.LocalDate;

public record CardDTO(
        String id,
        String question,
        String answer,
        BoxDTO box,
        LocalDate lastReview,
        LocalDate nextReview,
        CategoryDTO category
) {
    public static CardDTO toDTO(Card card) {
        return new CardDTO(
                card.id(),
                card.question(),
                card.answer(),
                BoxDTO.toDTO(card.box()),
                card.lastReviewedDate(),
                card.nextReviewDate(),
                CategoryDTO.toDTO(card.category())
        );
    }
}
