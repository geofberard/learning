package com.gberard.learning.application.dto.card;

import java.time.LocalDate;

public record CreateCardDTO(
        String question,
        String answer,
        String boxId,
        LocalDate lastReview,
        LocalDate nextReview,
        String categoryId
) {
}
