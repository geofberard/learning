package com.gberard.learning.domain.model;

import java.time.LocalDate;

public record Card(
        String id,
        String question,
        String answer,
        Box box,
        LocalDate lastReviewedDate,
        LocalDate nextReviewDate,
        Category category
) implements Identified {
}
