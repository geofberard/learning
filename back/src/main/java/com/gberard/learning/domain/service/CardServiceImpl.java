package com.gberard.learning.domain.service;

import com.gberard.learning.domain.model.Card;
import com.gberard.learning.domain.port.input.CardService;
import com.gberard.learning.domain.port.output.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
