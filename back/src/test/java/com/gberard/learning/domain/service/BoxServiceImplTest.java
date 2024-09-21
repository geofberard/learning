package com.gberard.learning.domain.service;

import com.gberard.learning.domain.model.Box;
import com.gberard.learning.domain.port.output.BoxRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BoxServiceImplTest {

    @Mock
    private BoxRepository repository;

    @InjectMocks
    private BoxServiceImpl boxService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindAllBoxes() {
        // Given
        Box box = new Box("1", "Box 1", 1, 10);
        when(repository.readAll()).thenReturn(List.of(box));

        // When
        List<Box> boxes = boxService.findAll();

        // Then
        assertThat(boxes).containsExactly(box);
        verify(repository).readAll();
    }

    @Test
    void shouldFindBoxById() {
        // Given
        Box box = new Box("1", "Box 1", 1, 10);
        when(repository.read("1")).thenReturn(Optional.of(box));

        // When
        Optional<Box> result = boxService.findById("1");

        // Then
        assertThat(result).contains(box);
        verify(repository).read("1");
    }

    @Test
    void shouldReturnEmptyIfBoxNotFound() {
        // Given
        when(repository.read("1")).thenReturn(Optional.empty());

        // When
        Optional<Box> result = boxService.findById("1");

        // Then
        assertThat(result).isEmpty();
        verify(repository).read("1");
    }

    @Test
    void shouldThrowIfBoxNotFound() {
        // Given
        when(repository.readOrThrow("1")).thenThrow(new IllegalArgumentException("Box not found"));

        // When / Then
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> boxService.findByIdOrThrow("1"));
        verify(repository).readOrThrow("1");
    }

    @Test
    void shouldCreateBox() {
        // Given
        Box box = new Box(null, "Box 1", 1, 10);
        when(repository.create(any(Box.class))).thenReturn(new Box("1", "Box 1", 1, 10));

        // When
        Box createdBox = boxService.create("Box 1", 1, 10);

        // Then
        assertThat(createdBox).isNotNull();
        assertThat(createdBox.id()).isEqualTo("1");
        verify(repository).create(any(Box.class));
    }

    @Test
    void shouldUpdateBox() {
        // Given
        Box box = new Box("1", "Old Name", 1, 10);
        when(repository.readOrThrow("1")).thenReturn(box);
        when(repository.create(any(Box.class))).thenReturn(new Box("1", "New Name", 1, 10));

        // When
        Box updatedBox = boxService.update("1", "New Name", 1, 10);

        // Then
        assertThat(updatedBox.name()).isEqualTo("New Name");
        verify(repository).readOrThrow("1");
        verify(repository).create(any(Box.class));
    }

    @Test
    void shouldDeleteBox() {
        // Given
        Box box = new Box("1", "Box 1", 1, 10);
        when(repository.read("1")).thenReturn(Optional.of(box));

        // When
        boolean result = boxService.delete("1");

        // Then
        assertThat(result).isTrue();
        verify(repository).read("1");
        verify(repository).delete(box);
    }

    @Test
    void shouldNotDeleteIfBoxNotFound() {
        // Given
        when(repository.read("1")).thenReturn(Optional.empty());

        // When
        boolean result = boxService.delete("1");

        // Then
        assertThat(result).isFalse();
        verify(repository).read("1");
        verify(repository, never()).delete(any(Box.class));
    }

    @Test
    void shouldFindFirstBox() {
        // Given
        Box box = new Box("1", "Box 1", 1, 10);
        when(repository.findByPosition(1)).thenReturn(Optional.of(box));

        // When
        Optional<Box> result = boxService.findFirst();

        // Then
        assertThat(result).contains(box);
        verify(repository).findByPosition(1);
    }

    @Test
    void shouldFindNextBox() {
        // Given
        Box currentBox = new Box("1", "Box 1", 1, 10);
        Box nextBox = new Box("2", "Box 2", 2, 20);
        when(repository.findByPosition(2)).thenReturn(Optional.of(nextBox));

        // When
        Optional<Box> result = boxService.findNext(currentBox);

        // Then
        assertThat(result).contains(nextBox);
        verify(repository).findByPosition(2);
    }

}
