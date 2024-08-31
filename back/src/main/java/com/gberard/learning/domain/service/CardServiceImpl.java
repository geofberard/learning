package com.gberard.learning.domain.service;

import com.gberard.learning.domain.model.Box;
import com.gberard.learning.domain.model.Card;
import com.gberard.learning.domain.port.input.CardService;
import com.gberard.learning.domain.port.output.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository repository;

    public CardServiceImpl(CardRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Card> findAll() {
        return repository.readAll();
    }

    @Override
    public Optional<Card> findById(String id) {
        return repository.read(id);
    }

    @Override
    public boolean delete(String id) {
        Optional<Card> box = findById(id);
        box.ifPresent(repository::delete);
        return box.isPresent();
    }

}
