package com.gberard.learning.application.controller;

import com.gberard.learning.application.dto.BoxDTO;
import com.gberard.learning.domain.port.input.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boxes")
public class BoxController {

    private final BoxService boxService;

    public BoxController(BoxService boxService) {
        this.boxService = boxService;
    }

    @GetMapping
    public ResponseEntity<List<BoxDTO>> findBoxes() {
        List<BoxDTO> boxes = boxService.findAll().stream()
                .map(BoxDTO::toDTO)
                .toList();

        return ResponseEntity.ok(boxes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoxDTO> findBoxById(@PathVariable String id) {
        return boxService.findById(id)
                .map(BoxDTO::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
