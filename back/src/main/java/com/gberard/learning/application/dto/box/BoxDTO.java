package com.gberard.learning.application.dto.box;

import com.gberard.learning.domain.model.Box;

public record BoxDTO(String id, String name, int position, int intervalDays) {

    public static BoxDTO toDTO(Box box) {
        return new BoxDTO(
                box.id(),
                box.name(),
                box.position(),
                box.intervalDays()
        );
    }

}
