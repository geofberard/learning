package com.gberard.learning.application.controller;

import com.gberard.learning.application.dto.card.CardDTO;
import com.gberard.learning.application.dto.card.CreateCardDTO;
import com.gberard.learning.application.dto.card.UpdateCardDTO;
import com.gberard.learning.domain.model.Card;
import com.gberard.learning.domain.port.input.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<List<CardDTO>> findCards() {
        List<CardDTO> card = cardService.findAll().stream()
                .map(CardDTO::toDTO)
                .toList();

        return ResponseEntity.ok(card);
    }

    @PostMapping
    public ResponseEntity<CardDTO> createCard(@RequestBody CreateCardDTO createCardDTO) {
        Card newCard = cardService.create(
                createCardDTO.question(),
                createCardDTO.answer(),
                createCardDTO.boxId(),
                createCardDTO.lastReview(),
                createCardDTO.nextReview(),
                createCardDTO.categoryId()
        );
        return ResponseEntity.ok(CardDTO.toDTO(newCard));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDTO> findCardById(@PathVariable String id) {
        return cardService.findById(id)
                .map(CardDTO::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardDTO> updateCard(@PathVariable String id, @RequestBody UpdateCardDTO updateCardDTO) {
        Card updatedCard = cardService.update(
                id,
                updateCardDTO.question(),
                updateCardDTO.answer(),
                updateCardDTO.boxId(),
                updateCardDTO.lastReview(),
                updateCardDTO.nextReview(),
                updateCardDTO.categoryId()
        );
        return ResponseEntity.ok(CardDTO.toDTO(updatedCard));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable String id) {
        return cardService.delete(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/{id}/promote")
    public ResponseEntity<Void> promoteCard(@PathVariable String id) {
        cardService.promote(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/demote")
    public ResponseEntity<Void>demoteCard(@PathVariable String id) {
        cardService.demote(id);
        return ResponseEntity.noContent().build();
    }

}
