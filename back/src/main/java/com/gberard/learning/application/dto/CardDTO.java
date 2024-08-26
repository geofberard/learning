package com.gberard.learning.application.dto;

import com.gberard.learning.domain.model.Card;

import java.time.LocalDate;

public record CardDTO(
        String id,
        String question,
        String answer,
        BoxDTO box,
        LocalDate lastReviewedDate,
        LocalDate nextReviewDate,
        CategoryDTO category
) {
    public static CardDTO toDTO(Card card) {
        return new CardDTO(
                card.id(),
                card.question(),
                card.answer(),
                BoxDTO.toDTO(card.box()),
                card.lastReviewedDate(),
                card.lastReviewedDate(),
                CategoryDTO.toDTO(card.category())
        );
    }
}
