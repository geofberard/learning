package com.gberard.learning.application.dto.box;

import static org.assertj.core.api.Assertions.assertThat;

import com.gberard.learning.domain.model.Box;
import org.junit.jupiter.api.Test;

public class BoxDTOTest {

    @Test
    public void testToDTO() {
        // Given
        Box box = new Box("boxId", "Review Box", 1, 7);  // Création d'une box simulée

        // When
        BoxDTO boxDTO = BoxDTO.toDTO(box);

        // Then
        assertThat(boxDTO.id()).isEqualTo("boxId");
        assertThat(boxDTO.name()).isEqualTo("Review Box");
        assertThat(boxDTO.position()).isEqualTo(1);
        assertThat(boxDTO.intervalDays()).isEqualTo(7);
    }

}

