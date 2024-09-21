package com.gberard.learning.application.controller;

import com.gberard.learning.application.dto.box.BoxDTO;
import com.gberard.learning.application.dto.card.CardDTO;
import com.gberard.learning.application.dto.card.CreateCardDTO;
import com.gberard.learning.application.dto.card.UpdateCardDTO;
import com.gberard.learning.application.dto.category.CategoryDTO;
import com.gberard.learning.domain.model.Box;
import com.gberard.learning.domain.model.Card;
import com.gberard.learning.domain.model.Category;
import com.gberard.learning.domain.port.input.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CardControllerTest {

    @Mock
    private CardService cardService;

    @InjectMocks
    private CardController cardController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllCards() {
        // Given
        Box box = new Box("1", "Test Box", 1, 30);
        Category category = new Category("1", "Test Category");
        Card card = new Card("1", "What is Java?", "A programming language", box, LocalDate.now(), LocalDate.now().plusDays(1), category);
        when(cardService.findAll()).thenReturn(Collections.singletonList(card));

        // When
        ResponseEntity<List<CardDTO>> response = cardController.findCards();

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody()).containsExactly(new CardDTO("1", "What is Java?", "A programming language", new BoxDTO("1", "Test Box", 1, 30), LocalDate.now(), LocalDate.now().plusDays(1), new CategoryDTO("1", "Test Category")));
        verify(cardService).findAll();
    }

    @Test
    void testCreateCard() {
        // Given
        Box box = new Box("1", "Test Box", 1, 30);
        Category category = new Category("1", "Test Category");
        CreateCardDTO createCardDTO = new CreateCardDTO("What is Java?", "A programming language", "boxId", LocalDate.now(), LocalDate.now().plusDays(1), "categoryId");
        Card newCard = new Card("2", "What is Java?", "A programming language", box, LocalDate.now(), LocalDate.now().plusDays(1), category);
        when(cardService.create(
                createCardDTO.question(),
                createCardDTO.answer(),
                createCardDTO.boxId(),
                createCardDTO.lastReview(),
                createCardDTO.nextReview(),
                createCardDTO.categoryId()
        )).thenReturn(newCard);

        // When
        ResponseEntity<CardDTO> response = cardController.createCard(createCardDTO);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(new CardDTO("2", "What is Java?", "A programming language", new BoxDTO("1", "Test Box", 1, 30), LocalDate.now(), LocalDate.now().plusDays(1), new CategoryDTO("1", "Test Category")));
        verify(cardService).create(
                createCardDTO.question(),
                createCardDTO.answer(),
                createCardDTO.boxId(),
                createCardDTO.lastReview(),
                createCardDTO.nextReview(),
                createCardDTO.categoryId()
        );
    }

    @Test
    void testFindCardByIdFound() {
        // Given
        Box box = new Box("1", "Test Box", 1, 30);
        Category category = new Category("1", "Test Category");
        String id = "1";
        Card card = new Card(id, "What is Java?", "A programming language", box, LocalDate.now(), LocalDate.now().plusDays(1), category);
        when(cardService.findById(id)).thenReturn(Optional.of(card));

        // When
        ResponseEntity<CardDTO> response = cardController.findCardById(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(new CardDTO("1", "What is Java?", "A programming language", new BoxDTO("1", "Test Box", 1, 30), LocalDate.now(), LocalDate.now().plusDays(1), new CategoryDTO("1", "Test Category")));
        verify(cardService).findById(id);
    }

    @Test
    void testFindCardByIdNotFound() {
        // Given
        String id = "non-existent-id";
        when(cardService.findById(id)).thenReturn(Optional.empty());

        // When
        ResponseEntity<CardDTO> response = cardController.findCardById(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(cardService).findById(id);
    }

    @Test
    void testUpdateCard() {
        // Given
        Box box = new Box("1", "Test Box", 1, 30);
        Category category = new Category("1", "Test Category");
        String id = "1";
        UpdateCardDTO updateCardDTO = new UpdateCardDTO("Updated question", "Updated answer", "boxId", LocalDate.now(), LocalDate.now().plusDays(1), "categoryId");
        Card updatedCard = new Card(id, "Updated question", "Updated answer", box, LocalDate.now(), LocalDate.now().plusDays(1), category);
        when(cardService.update(
                id,
                updateCardDTO.question(),
                updateCardDTO.answer(),
                updateCardDTO.boxId(),
                updateCardDTO.lastReview(),
                updateCardDTO.nextReview(),
                updateCardDTO.categoryId()
        )).thenReturn(updatedCard);

        // When
        ResponseEntity<CardDTO> response = cardController.updateCard(id, updateCardDTO);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(new CardDTO("1", "Updated question", "Updated answer", new BoxDTO("1", "Test Box", 1, 30), LocalDate.now(), LocalDate.now().plusDays(1), new CategoryDTO("1", "Test Category")));
        verify(cardService).update(
                id,
                updateCardDTO.question(),
                updateCardDTO.answer(),
                updateCardDTO.boxId(),
                updateCardDTO.lastReview(),
                updateCardDTO.nextReview(),
                updateCardDTO.categoryId()
        );
    }

    @Test
    void testDeleteCardSuccess() {
        // Given
        String id = "1";
        when(cardService.delete(id)).thenReturn(true);

        // When
        ResponseEntity<Void> response = cardController.deleteCard(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(cardService).delete(id);
    }

    @Test
    void testDeleteCardNotFound() {
        // Given
        String id = "non-existent-id";
        when(cardService.delete(id)).thenReturn(false);

        // When
        ResponseEntity<Void> response = cardController.deleteCard(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(cardService).delete(id);
    }

    @Test
    void testPromoteCard() {
        // Given
        String id = "1";

        // When
        ResponseEntity<Void> response = cardController.promoteCard(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(cardService).promote(id);
    }

    @Test
    void testDemoteCard() {
        // Given
        String id = "1";

        // When
        ResponseEntity<Void> response = cardController.demoteCard(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(cardService).demote(id);
    }
}
