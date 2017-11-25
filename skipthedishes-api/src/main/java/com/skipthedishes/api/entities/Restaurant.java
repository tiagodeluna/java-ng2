package com.skipthedishes.api.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Set;

/**
 * Created by igorhara on 23/11/2017.
 */
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Restaurant {

    @Id
    private String id;

    private String name;

    private String address;

    private String timeToWait;

    private String deliveryFee;

    private Set<CategoriesEnum> categories;

    private Set<TagsEnum> tags;

}
