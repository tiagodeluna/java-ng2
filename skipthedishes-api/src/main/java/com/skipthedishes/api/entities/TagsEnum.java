package com.skipthedishes.api.entities;

import lombok.Getter;

@Getter
public enum TagsEnum {

    STEAK("Steak"),
    CHICKEN("Chicken"),
    PORK("Pork"),
    JAPANESE("Japanese"),
    CHINESE("Chinese"),
    THAI("Thai"),
    SALADS("Salads"),
    DESSERTS("Desserts");

    private String name;

    TagsEnum(String name) {
        this.name = name;
    }
}
