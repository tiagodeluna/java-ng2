package com.skipthedishes.api.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Customer {

    @Id
    private String id;

    private String firstName;

    private String lastName;


}
