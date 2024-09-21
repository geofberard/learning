package com.gberard.learning.domain.model;

import java.time.LocalDate;

public class CardBuilder {

    public static CardBuilder builder() {
        return new CardBuilder();
    }

    public static CardBuilder from(Card card) {
        return new CardBuilder()
                .id(card.id())
                .question(card.question())
                .answer(card.answer())
                .box(card.box())
                .lastReviewedDate(card.lastReviewedDate())
                .nextReviewDate(card.nextReviewDate())
                .category(card.category());
    }

    private String id;
    private String question;
    private String answer;
    private Box box;
    private LocalDate lastReviewedDate;
    private LocalDate nextReviewDate;
    private Category category;

    public CardBuilder id(String id) {
        this.id = id;
        return this;
    }

    public CardBuilder question(String question) {
        this.question = question;
        return this;
    }

    public CardBuilder answer(String answer) {
        this.answer = answer;
        return this;
    }

    public CardBuilder box(Box box) {
        this.box = box;
        return this;
    }

    public CardBuilder lastReviewedDate(LocalDate lastReviewedDate) {
        this.lastReviewedDate = lastReviewedDate;
        return this;
    }

    public CardBuilder nextReviewDate(LocalDate nextReviewDate) {
        this.nextReviewDate = nextReviewDate;
        return this;
    }

    public CardBuilder category(Category category) {
        this.category = category;
        return this;
    }

    public Card build() {
        return new Card(id, question, answer, box, lastReviewedDate, nextReviewDate, category);
    }

}
