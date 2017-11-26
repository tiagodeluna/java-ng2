package com.skipthedishes.api.services.event;

import com.skipthedishes.api.entities.Order;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderFinishedEvent extends ApplicationEvent {

    private Order order;

    public OrderFinishedEvent(Object source, Order order) {
        super(source);

        this.order = order;
    }
}
