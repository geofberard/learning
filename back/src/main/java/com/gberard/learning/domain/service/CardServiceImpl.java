package com.gberard.learning.domain.service;

import com.gberard.learning.domain.model.Box;
import com.gberard.learning.domain.model.Card;
import com.gberard.learning.domain.model.Category;
import com.gberard.learning.domain.port.input.CardService;
import com.gberard.learning.domain.port.output.BoxRepository;
import com.gberard.learning.domain.port.output.CardRepository;
import com.gberard.learning.domain.port.output.CategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CategoryRepository categoryRepository;
    private final BoxRepository boxRepository;

    public CardServiceImpl(CardRepository cardRepository, CategoryRepository categoryRepository, BoxRepository boxRepository) {
        this.cardRepository = cardRepository;
        this.categoryRepository = categoryRepository;
        this.boxRepository = boxRepository;
    }

    @Override
    public List<Card> findAll() {
        return cardRepository.readAll();
    }

    @Override
    public Optional<Card> findById(String id) {
        return cardRepository.read(id);
    }

    @Override
    public Card create(String question, String answer, String boxId, LocalDate lastReviewedDate, LocalDate nextReviewDate, String categoryId) {
        Box box = boxRepository.readOrThrow(boxId);
        Category category = categoryRepository.readOrThrow(categoryId);

        return cardRepository.create(new Card(null, question, answer, box, lastReviewedDate, nextReviewDate, category));
    }

    @Override
    public Card update(String id, String question, String answer, String boxId, LocalDate lastReviewedDate, LocalDate nextReviewDate, String categoryId) {
        Card card = cardRepository.readOrThrow(id);
        Box box = boxRepository.readOrThrow(boxId);
        Category category = categoryRepository.readOrThrow(categoryId);

        return cardRepository.update(new Card(card.id(), question, answer, box, lastReviewedDate, nextReviewDate, category));
    }

    @Override
    public boolean delete(String id) {
        Optional<Card> box = findById(id);
        box.ifPresent(cardRepository::delete);
        return box.isPresent();
    }

}
