package com.skipthedishes.api.entities;

public enum CategoriesEnum {

    MEAT("Meat For a Viking"),
    ORIENTAL("Oriental"),
    VEGAN("Vegan For Life");

    private String name;

    CategoriesEnum(String name) {
        this.name = name;
    }
}
