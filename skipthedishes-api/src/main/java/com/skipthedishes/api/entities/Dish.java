package com.skipthedishes.api.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Set;

/**
 * Created by igorhara on 23/11/2017.
 */
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Dish {

    @Id
    private String id;

    private String restaurantId;

    private String name;

    private String description;

    private Set<TagsEnum> tags;

    private Double rating;


}
