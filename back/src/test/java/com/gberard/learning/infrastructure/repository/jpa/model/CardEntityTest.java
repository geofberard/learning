package com.gberard.learning.infrastructure.repository.jpa.model;

import com.gberard.learning.domain.model.Box;
import com.gberard.learning.domain.model.Card;
import com.gberard.learning.domain.model.Category;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class CardEntityTest {

    @Test
    void testToDomain() {
        // Given
        BoxEntity boxEntity = new BoxEntity("box_1", "Sample Box", 1, 3);
        CategoryEntity categoryEntity = new CategoryEntity("category_1", "Sample Category");
        CardEntity cardEntity = new CardEntity("card_1",
                "What is Java?",
                "A programming language.",
                boxEntity,
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                categoryEntity);

        // When
        Card card = CardEntity.toDomain(cardEntity);

        // Then
        assertThat(card.id()).isEqualTo("card_1");
        assertThat(card.question()).isEqualTo("What is Java?");
        assertThat(card.answer()).isEqualTo("A programming language.");
        assertThat(card.box()).isEqualTo(BoxEntity.toDomain(boxEntity));
        assertThat(card.lastReviewedDate()).isEqualTo(LocalDate.now());
        assertThat(card.nextReviewDate()).isEqualTo(LocalDate.now().plusDays(1));
        assertThat(card.category()).isEqualTo(CategoryEntity.toDomain(categoryEntity));
    }

    @Test
    void testToEntity() {
        // Given
        Box box = new Box("box_1", "Sample Box", 1, 3);
        Category category = new Category("category_1", "Sample Category");
        Card card = new Card("card_1",
                "What is Java?",
                "A programming language.",
                box,
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                category);

        // When
        CardEntity convertedCardEntity = CardEntity.toEntity(card);

        // Then
        assertThat(convertedCardEntity.getId()).isEqualTo("card_1");
        assertThat(convertedCardEntity.getQuestion()).isEqualTo("What is Java?");
        assertThat(convertedCardEntity.getAnswer()).isEqualTo("A programming language.");
        assertThat(convertedCardEntity.getBox().getId()).isEqualTo(BoxEntity.toEntity(box).getId());
        assertThat(convertedCardEntity.getLastReviewedDate()).isEqualTo(LocalDate.now());
        assertThat(convertedCardEntity.getNextReviewDate()).isEqualTo(LocalDate.now().plusDays(1));
        assertThat(convertedCardEntity.getCategory().getId()).isEqualTo(CategoryEntity.toEntity(category).getId());
    }

    @Test
    void testGenerateUUIDOnPersist() {
        // Given
        CardEntity cardEntity = new CardEntity();

        // When
        cardEntity.generateUUID();

        // Then
        assertThat(cardEntity.getId()).isNotEmpty();
        assertThat(cardEntity.getId()).startsWith("card_");
    }


}


