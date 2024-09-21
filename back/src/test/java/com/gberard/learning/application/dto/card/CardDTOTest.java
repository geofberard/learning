package com.gberard.learning.application.dto.card;

import com.gberard.learning.application.dto.box.BoxDTO;
import com.gberard.learning.application.dto.category.CategoryDTO;
import com.gberard.learning.domain.model.Box;
import com.gberard.learning.domain.model.Card;
import com.gberard.learning.domain.model.Category;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDTOTest {

    @Test
    public void testToDTO() {
        // Given
        Box box = new Box("boxId", "boxName", 1, 7);  // Création d'une box simulée
        Category category = new Category("categoryId", "categoryName");  // Création d'une catégorie simulée
        Card card = new Card(
                "cardId",
                "What is Java?",
                "A programming language",
                box,
                LocalDate.of(2024, 9, 22),
                LocalDate.of(2024, 10, 22),
                category
        );

        // When
        CardDTO cardDTO = CardDTO.toDTO(card);

        // Then
        assertThat(cardDTO.id()).isEqualTo("cardId");
        assertThat(cardDTO.question()).isEqualTo("What is Java?");
        assertThat(cardDTO.answer()).isEqualTo("A programming language");
        assertThat(cardDTO.box()).isEqualTo(BoxDTO.toDTO(box));
        assertThat(cardDTO.lastReview()).isEqualTo(LocalDate.of(2024, 9, 22));
        assertThat(cardDTO.nextReview()).isEqualTo(LocalDate.of(2024, 10, 22));
        assertThat(cardDTO.category()).isEqualTo(CategoryDTO.toDTO(category));
    }

}
