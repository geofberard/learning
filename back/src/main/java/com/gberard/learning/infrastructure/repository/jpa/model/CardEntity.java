package com.gberard.learning.infrastructure.repository.jpa.model;

import com.gberard.learning.domain.model.Box;
import com.gberard.learning.domain.model.Card;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "card")
@Table(name = "cards")
public class CardEntity {

    @Id
    private String id;

    private String question;
    private String answer;

    @ManyToOne
    @JoinColumn(name = "box_id")
    private BoxEntity box;

    @Column(name = "last_reviewed_date")
    private LocalDate lastReviewedDate;

    @Column(name = "next_review_date")
    private LocalDate nextReviewDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @PrePersist
    public void generateUUID() {
        if (this.id == null) {
            this.id = "card_" + UUID.randomUUID();
        }
    }

    public static Card toDomain(CardEntity cardEntity) {
        return new Card(
                cardEntity.id,
                cardEntity.question,
                cardEntity.answer,
                BoxEntity.toDomain(cardEntity.box),
                cardEntity.lastReviewedDate,
                cardEntity.nextReviewDate,
                CategoryEntity.toDomain(cardEntity.category)
        );
    }

    public static CardEntity toEntity(Card card) {
        CardEntity cardEntity = new CardEntity();
        cardEntity.id = card.id();
        cardEntity.question=card.question();
        cardEntity.answer=card.answer();
        cardEntity.box=BoxEntity.toEntity(card.box());
        cardEntity.lastReviewedDate=card.lastReviewedDate();
        cardEntity.nextReviewDate=card.nextReviewDate();
        cardEntity.category=CategoryEntity.toEntity(card.category());
        return cardEntity;
    }

}
