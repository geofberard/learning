package com.gberard.learning.domain.service;

import com.gberard.learning.domain.model.Box;
import com.gberard.learning.domain.model.Card;
import com.gberard.learning.domain.model.CardBuilder;
import com.gberard.learning.domain.model.Category;
import com.gberard.learning.domain.port.output.CardRepository;
import com.gberard.learning.domain.port.output.CategoryRepository;
import com.gberard.learning.domain.port.input.BoxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CardServiceImplTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private BoxService boxService;

    @InjectMocks
    private CardServiceImpl cardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindAllCards() {
        // Given
        Card card = new Card("1", "Question", "Answer", null, LocalDate.now(), LocalDate.now().plusDays(10), null);
        when(cardRepository.readAll()).thenReturn(List.of(card));

        // When
        List<Card> cards = cardService.findAll();

        // Then
        assertThat(cards).containsExactly(card);
        verify(cardRepository).readAll();
    }

    @Test
    void shouldFindCardById() {
        // Given
        Card card = new Card("1", "Question", "Answer", null, LocalDate.now(), LocalDate.now().plusDays(10), null);
        when(cardRepository.read("1")).thenReturn(Optional.of(card));

        // When
        Optional<Card> result = cardService.findById("1");

        // Then
        assertThat(result).contains(card);
        verify(cardRepository).read("1");
    }

    @Test
    void shouldCreateCard() {
        // Given
        Box box = new Box("1", "Box 1", 1, 10);
        Category category = new Category("1", "Category 1");
        LocalDate lastReviewed = LocalDate.now();
        LocalDate nextReview = LocalDate.now().plusDays(10);

        when(boxService.findByIdOrThrow("1")).thenReturn(box);
        when(categoryRepository.readOrThrow("1")).thenReturn(category);
        when(cardRepository.create(any(Card.class)))
                .thenReturn(new Card("1", "Question", "Answer", box, lastReviewed, nextReview, category));

        // When
        Card createdCard = cardService.create("Question", "Answer", "1", lastReviewed, nextReview, "1");

        // Then
        assertThat(createdCard).isNotNull();
        assertThat(createdCard.id()).isEqualTo("1");
        assertThat(createdCard.box()).isEqualTo(box);
        assertThat(createdCard.category()).isEqualTo(category);
        verify(cardRepository).create(any(Card.class));
    }

    @Test
    void shouldUpdateCard() {
        // Given
        Card card = new Card("1", "Old Question", "Old Answer", new Box("1", "Box 1", 1, 10), LocalDate.now(), LocalDate.now().plusDays(10), new Category("1", "Category 1"));
        Box box = new Box("2", "Box 2", 2, 20);
        Category category = new Category("2", "Category 2");

        when(cardRepository.readOrThrow("1")).thenReturn(card);
        when(boxService.findByIdOrThrow("2")).thenReturn(box);
        when(categoryRepository.readOrThrow("2")).thenReturn(category);
        when(cardRepository.update(any(Card.class)))
                .thenReturn(new Card("1", "New Question", "New Answer", box, LocalDate.now(), LocalDate.now().plusDays(20), category));

        // When
        Card updatedCard = cardService.update("1", "New Question", "New Answer", "2", LocalDate.now(), LocalDate.now().plusDays(20), "2");

        // Then
        assertThat(updatedCard.question()).isEqualTo("New Question");
        assertThat(updatedCard.answer()).isEqualTo("New Answer");
        assertThat(updatedCard.box()).isEqualTo(box);
        assertThat(updatedCard.category()).isEqualTo(category);
        verify(cardRepository).readOrThrow("1");
        verify(cardRepository).update(any(Card.class));
    }

    @Test
    void shouldDeleteCard() {
        // Given
        Card card = new Card("1", "Question", "Answer", null, LocalDate.now(), LocalDate.now().plusDays(10), null);
        when(cardRepository.read("1")).thenReturn(Optional.of(card));

        // When
        boolean result = cardService.delete("1");

        // Then
        assertThat(result).isTrue();
        verify(cardRepository).read("1");
        verify(cardRepository).delete(card);
    }

    @Test
    void shouldPromoteCard() {
        // Given
        Card card = new Card("1", "Question", "Answer", new Box("1", "Box 1", 1, 10), LocalDate.now(), LocalDate.now().plusDays(10), new Category("1", "Category 1"));
        Box nextBox = new Box("2", "Box 2", 2, 20);
        when(cardRepository.readOrThrow("1")).thenReturn(card);
        when(boxService.findNext(card.box())).thenReturn(Optional.of(nextBox));
        when(cardRepository.update(any(Card.class))).thenReturn(card);

        // When
        boolean promoted = cardService.promote("1");

        // Then
        assertThat(promoted).isTrue();
        verify(cardRepository).readOrThrow("1");
        verify(boxService).findNext(card.box());
        verify(cardRepository).update(any(Card.class));
    }

    @Test
    void shouldDemoteCard() {
        // Given
        Card card = new Card("1", "Question", "Answer", new Box("3", "Box 3", 3, 30), LocalDate.now(), LocalDate.now().plusDays(30), new Category("1", "Category 1"));
        Box firstBox = new Box("1", "Box 1", 1, 10);
        when(cardRepository.readOrThrow("1")).thenReturn(card);
        when(boxService.findFirst()).thenReturn(Optional.of(firstBox));
        when(cardRepository.update(any(Card.class))).thenReturn(card);

        // When
        boolean demoted = cardService.demote("1");

        // Then
        assertThat(demoted).isTrue();
        verify(cardRepository).readOrThrow("1");
        verify(boxService).findFirst();
        verify(cardRepository).update(any(Card.class));
    }

}
