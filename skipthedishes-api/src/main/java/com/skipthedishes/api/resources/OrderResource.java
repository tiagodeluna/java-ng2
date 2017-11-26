package com.skipthedishes.api.resources;

import com.skipthedishes.api.entities.Dish;
import com.skipthedishes.api.entities.Order;
import com.skipthedishes.api.entities.PaymentMethodsEnum;
import com.skipthedishes.api.entities.Restaurant;
import com.skipthedishes.api.exceptions.InvalidOrderTotalException;
import com.skipthedishes.api.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> create(@Valid @RequestBody Order order) {
        order.setDate(LocalDateTime.now());
        Order orderSaved = orderService.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable String id, @Valid @RequestBody Order order) {
        return ResponseEntity.ok(orderService.update(id, order));
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        List<Order> orderList = orderService.findAll();
        return orderList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(orderList);
    }

    @PostMapping(params = "/{id}/finish")
    public ResponseEntity<Boolean> finish(@PathVariable String id, @Valid @RequestBody PaymentMethodsEnum paymentMethod) throws InvalidOrderTotalException {
        Boolean isOrderFinished = orderService.finishOrder(id, paymentMethod);
        return ResponseEntity.status(HttpStatus.CREATED).body(isOrderFinished);
    }
}
