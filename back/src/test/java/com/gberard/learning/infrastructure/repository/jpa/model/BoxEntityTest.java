package com.gberard.learning.infrastructure.repository.jpa.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.gberard.learning.domain.model.Box;
import org.junit.jupiter.api.Test;

public class BoxEntityTest {

    @Test
    public void testToDomain() {
        // Given
        BoxEntity boxEntity = new BoxEntity("box_1", "Review Box", 1, 7);

        // When
        Box box = BoxEntity.toDomain(boxEntity);

        // Then
        assertThat(box.id()).isEqualTo("box_1");
        assertThat(box.name()).isEqualTo("Review Box");
        assertThat(box.position()).isEqualTo(1);
        assertThat(box.intervalDays()).isEqualTo(7);
    }

    @Test
    public void testToEntity() {
        // Given
        Box box = new Box("box_1", "Review Box", 1, 7);

        // When
        BoxEntity boxEntity = BoxEntity.toEntity(box);

        // Then
        assertThat(boxEntity.getId()).isEqualTo("box_1");
        assertThat(boxEntity.getName()).isEqualTo("Review Box");
        assertThat(boxEntity.getPosition()).isEqualTo(1);
        assertThat(boxEntity.getIntervalDays()).isEqualTo(7);
    }

    @Test
    public void testGenerateUUIDOnPersist() {
        // Given
        BoxEntity boxEntity = new BoxEntity();

        // When
        boxEntity.generateUUID();

        // Then
        assertThat(boxEntity.getId()).isNotNull();
        assertThat(boxEntity.getId()).startsWith("box_");
    }
}


