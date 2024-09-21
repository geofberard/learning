package com.gberard.learning.application.controller;

import com.gberard.learning.application.dto.box.BoxDTO;
import com.gberard.learning.application.dto.box.CreateBoxDTO;
import com.gberard.learning.application.dto.box.UpdateBoxDTO;
import com.gberard.learning.domain.model.Box;
import com.gberard.learning.domain.port.input.BoxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BoxControllerTest {

    @Mock
    private BoxService boxService;

    @InjectMocks
    private BoxController boxController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllBoxes() {
        // Given
        Box box = new Box("1", "Test Box", 1, 30);
        when(boxService.findAll()).thenReturn(Collections.singletonList(box));

        // When
        ResponseEntity<List<BoxDTO>> response = boxController.findBoxes();

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody()).containsExactly(new BoxDTO("1", "Test Box", 1, 30));
        verify(boxService).findAll();
    }

    @Test
    void testCreateBox() {
        // Given
        CreateBoxDTO createBoxDTO = new CreateBoxDTO("New Box", 2, 10);
        Box newBox = new Box("2", "New Box", 2, 10);
        when(boxService.create(createBoxDTO.name(), createBoxDTO.position(), createBoxDTO.intervalDays())).thenReturn(newBox);

        // When
        ResponseEntity<BoxDTO> response = boxController.createBox(createBoxDTO);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(new BoxDTO("2", "New Box", 2, 10));
        verify(boxService).create(createBoxDTO.name(), createBoxDTO.position(), createBoxDTO.intervalDays());
    }

    @Test
    void testFindBoxByIdFound() {
        // Given
        String id = "1";
        Box box = new Box(id, "Test Box", 1, 30);
        when(boxService.findById(id)).thenReturn(Optional.of(box));

        // When
        ResponseEntity<BoxDTO> response = boxController.findBoxById(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(new BoxDTO("1", "Test Box", 1, 30));
        verify(boxService).findById(id);
    }

    @Test
    void testFindBoxByIdNotFound() {
        // Given
        String id = "non-existent-id";
        when(boxService.findById(id)).thenReturn(Optional.empty());

        // When
        ResponseEntity<BoxDTO> response = boxController.findBoxById(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(boxService).findById(id);
    }

    @Test
    void testUpdateBox() {
        // Given
        String id = "1";
        UpdateBoxDTO updateBoxDTO = new UpdateBoxDTO("Updated Box", 2, 15);
        Box updatedBox = new Box(id, "Updated Box", 2, 15);
        when(boxService.update(id, updateBoxDTO.name(), updateBoxDTO.position(), updateBoxDTO.intervalDays())).thenReturn(updatedBox);

        // When
        ResponseEntity<BoxDTO> response = boxController.updateBox(id, updateBoxDTO);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(new BoxDTO("1", "Updated Box", 2, 15));
        verify(boxService).update(id, updateBoxDTO.name(), updateBoxDTO.position(), updateBoxDTO.intervalDays());
    }

    @Test
    void testDeleteBoxSuccess() {
        // Given
        String id = "1";
        when(boxService.delete(id)).thenReturn(true);

        // When
        ResponseEntity<Void> response = boxController.deleteBox(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(boxService).delete(id);
    }

    @Test
    void testDeleteBoxNotFound() {
        // Given
        String id = "non-existent-id";
        when(boxService.delete(id)).thenReturn(false);

        // When
        ResponseEntity<Void> response = boxController.deleteBox(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(boxService).delete(id);
    }

}
