package com.skipthedishes.api.entities;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TagsEnum {

    SANDWICH("Sandwish"),
    JAPANESE("Japanese"),
    LUNCH("Lunch"),
    ITALIAN("Italian"),
    CHINESE("Chinese"),
    AMERICAN("American"),
    BREAKFAST("Breakfast"),
    MEXICAN("Mexican"),
    SALADS("Salads"),
    BBQ("BBQ"),
    FAST_FOOD("Fast Food");

    private String name;

    TagsEnum(String name) {
        this.name = name;
    }
}
