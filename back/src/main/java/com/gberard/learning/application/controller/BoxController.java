package com.gberard.learning.application.controller;

import com.gberard.learning.application.dto.box.BoxDTO;
import com.gberard.learning.application.dto.box.CreateBoxDTO;
import com.gberard.learning.application.dto.box.UpdateBoxDTO;
import com.gberard.learning.domain.model.Box;
import com.gberard.learning.domain.port.input.BoxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

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

    @PostMapping
    public ResponseEntity<BoxDTO> createBox(@RequestBody CreateBoxDTO createBoxDTO) {
        Box newBox = boxService.create(createBoxDTO.name(), createBoxDTO.position(), createBoxDTO.intervalDays());
        return ResponseEntity.ok(BoxDTO.toDTO(newBox));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoxDTO> findBoxById(@PathVariable String id) {
        return boxService.findById(id)
                .map(BoxDTO::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoxDTO> updateBox(@PathVariable String id, @RequestBody UpdateBoxDTO updateBoxDTO) {
        Box updatedBox = boxService.update(id, updateBoxDTO.name(), updateBoxDTO.position(), updateBoxDTO.intervalDays());
        return ResponseEntity.ok(BoxDTO.toDTO(updatedBox));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBox(@PathVariable String id) {
        return boxService.delete(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
