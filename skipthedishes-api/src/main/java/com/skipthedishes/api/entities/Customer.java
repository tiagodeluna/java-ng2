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
    public String id;

    public String firstName;

    public String lastName;


}
