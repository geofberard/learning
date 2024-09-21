package com.gberard.learning.domain.model;

public record Box(String id, String name, int position, int intervalDays) implements Identified {
}
