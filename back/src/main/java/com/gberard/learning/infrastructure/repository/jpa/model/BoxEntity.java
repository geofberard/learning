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

    private int position;

    private int intervalDays;

    public BoxEntity() {
    }

    public BoxEntity(String id, String name, int position, int intervalDays) {
        this.intervalDays = intervalDays;
        this.position = position;
        this.name = name;
        this.id = id;
    }

    @PrePersist
    public void generateUUID() {
        if (this.id == null) {
            this.id = "box_" + UUID.randomUUID();
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getIntervalDays() {
        return intervalDays;
    }

    public static Box toDomain(BoxEntity boxEntity) {
        return new Box(boxEntity.id, boxEntity.name, boxEntity.position, boxEntity.intervalDays);
    }

    public static BoxEntity toEntity(Box box) {
        BoxEntity boxEntity = new BoxEntity();
        boxEntity.id= box.id();
        boxEntity.name= box.name();
        boxEntity.position= box.position();
        boxEntity.intervalDays= box.intervalDays();
        return boxEntity;
    }

}
