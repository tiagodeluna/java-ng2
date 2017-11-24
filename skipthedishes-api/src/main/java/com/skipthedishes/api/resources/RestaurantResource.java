package com.skipthedishes.api.resources;

import com.skipthedishes.api.entities.Restaurant;
import com.skipthedishes.api.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantResource {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @PostMapping
    public ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant) {
        Restaurant restaurantSaved = restaurantRepository.save(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantSaved);
    }

    @PostMapping(path = "bulk")
    public ResponseEntity<List<Restaurant>> createBulk(@RequestBody List<Restaurant> restaurantList) {
        restaurantList.stream().forEach(restaurantRepository::save);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantList);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> findAll() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return restaurantList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(restaurantList);

    }
}
