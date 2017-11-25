package com.skipthedishes.api.resources;


import com.skipthedishes.api.entities.Customer;
import com.skipthedishes.api.entities.Dish;
import com.skipthedishes.api.entities.Order;
import com.skipthedishes.api.entities.Restaurant;
import com.skipthedishes.api.repositories.CustomerRepository;
import com.skipthedishes.api.services.CustomerService;
import com.skipthedishes.api.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerResource {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer) {
        Customer customerSaved = customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerSaved);
    }

    @PutMapping(path = "/{id}/add-favorite-dish", params = "dishId")
    public ResponseEntity<Customer> addFavoriteDish(@PathVariable String id, @RequestParam(name = "dishId") String dishId) {
        Customer customerSaved = customerService.addFavoriteDish(id, dishId);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerSaved);
    }

    @PutMapping(path = "/{id}/remove-favorite-dish", params = "dishId")
    public ResponseEntity<Customer> removeFavoriteDish(@PathVariable String id, @RequestParam(name = "dishId") String dishId) {
        Customer customerSaved = customerService.removeFavoriteDish(id, dishId);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerSaved);
    }

    @PutMapping(path = "/{id}/add-favorite-restaurant", params = "restaurantId")
    public ResponseEntity<Customer> addFavoriteRestaurant(@PathVariable String id, @RequestParam(name = "restaurantId") String restaurantId) {
        Customer customerSaved = customerService.addFavoriteRestaurant(id, restaurantId);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerSaved);
    }

    @PutMapping(path = "/{id}/remove-favorite-restaurant", params = "restaurantId")
    public ResponseEntity<Customer> removeFavorite(@PathVariable String id, @RequestParam(name = "restaurantId") String restaurantId) {
        Customer customerSaved = customerService.removeFavoriteRestaurant(id, restaurantId);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerSaved);
    }

    @PostMapping(path = "bulk")
    public ResponseEntity<List<Customer>> createBulk(@RequestBody List<Customer> customers) {
        customers.stream().forEach(customerRepository::save);
        return ResponseEntity.status(HttpStatus.CREATED).body(customers);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(customerList);

    }

    @GetMapping(params = "name")
    public ResponseEntity<List<Customer>> findByText(@RequestParam(name = "name") String name) {
        List<Customer> customerList = customerRepository.find(name);
        return customerList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(customerList);

    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<Order>> findOrdersById(@PathVariable String id) {
        List<Order> customerOrders = orderService.findByCustomerId(id);
        return customerOrders.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(customerOrders);
    }

}
