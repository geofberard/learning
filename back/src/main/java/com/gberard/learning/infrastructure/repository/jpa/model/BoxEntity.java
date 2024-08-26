package com.gberard.learning.infrastructure.repository.jpa.model;

import com.gberard.learning.domain.model.Box;
import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "box")
@Table(name = "boxes")
public class BoxEntity {

    @Id
    private String id;

    private String name;

    private int intervalDays;

    @PrePersist
    public void generateUUID() {
        if (this.id == null) {
            this.id = "box_" + UUID.randomUUID();
        }
    }

    public static Box toDomain(BoxEntity boxEntity) {
        return new Box(boxEntity.id, boxEntity.name, boxEntity.intervalDays);
    }

    public static BoxEntity toEntity(Box box) {
        BoxEntity boxEntity = new BoxEntity();
        boxEntity.id= box.id();
        boxEntity.name= box.name();
        boxEntity.intervalDays= box.intervalDays();
        return boxEntity;
    }

}
