package com.gberard.learning.application.controller;

import com.gberard.learning.application.dto.CardDTO;
import com.gberard.learning.domain.port.input.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<List<CardDTO>> findBoxes() {
        List<CardDTO> card = cardService.findAll().stream()
                .map(CardDTO::toDTO)
                .toList();

        return ResponseEntity.ok(card);
    }

}
