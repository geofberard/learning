package com.gberard.learning.application.dto;

import com.gberard.learning.domain.model.Box;

public record BoxDTO(String id, String name, int intervalDays) {

    public static BoxDTO toDTO(Box box) {
        return new BoxDTO(
                box.id(),
                box.name(),
                box.intervalDays()
        );
    }

}
