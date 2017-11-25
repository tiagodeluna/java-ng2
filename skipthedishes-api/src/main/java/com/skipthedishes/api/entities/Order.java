package com.skipthedishes.api.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by igorhara on 23/11/2017.
 */
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Order {

    @Id
    private String id;

    private LocalDateTime date;

    private Double total;

    private PaymentMethodsEnum paymentMethod;

    private String reviewText;

    private Integer reviewRating;

    private String customerId;

    private Set<Item> items;

    private OrderStatusEnum status;

    public Double getTotalInDishCoins() {
        return DishCoinCalculator.convert(this.total);
    }
}
