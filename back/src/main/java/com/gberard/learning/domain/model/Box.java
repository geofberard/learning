package com.gberard.learning.domain.model;

public record Box(String id, String name, int intervalDays) implements Identified {
}
