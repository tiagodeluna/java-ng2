package com.skipthedishes.api.resources;

import com.skipthedishes.api.entities.CategoriesEnum;
import com.skipthedishes.api.entities.Dish;
import com.skipthedishes.api.entities.Restaurant;
import com.skipthedishes.api.entities.TagsEnum;
import com.skipthedishes.api.repositories.RestaurantRepository;
import com.skipthedishes.api.services.DishService;
import com.skipthedishes.api.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantResource {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @PostMapping
    public ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant) {
        Restaurant restaurantSaved = restaurantRepository.save(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantSaved);
    }

    @PostMapping(path = "bulk")
    public ResponseEntity<List<Restaurant>> createBulk(@RequestBody List<Restaurant> restaurantList) {
        restaurantRepository.save(restaurantList);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantList);
    }

    @PutMapping(path = "bulk")
    public ResponseEntity<List<Restaurant>> updateBulk(@RequestBody List<Restaurant> restaurantList) {
        restaurantList.stream().forEach(r -> restaurantService.update(r.getId(), r));
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> update(@PathVariable String id, @Valid @RequestBody Restaurant restaurant) {
        return ResponseEntity.ok(restaurantService.update(id, restaurant));
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> findAll() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return restaurantList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(restaurantList);
    }

    @GetMapping(params = {"text", "offset", "size"})
    public ResponseEntity<List<Restaurant>> find(@RequestParam(name = "text") String text, @RequestParam(name = "offset") int offset, @RequestParam(name = "size") int size) {
        List<Restaurant> restaurantList = restaurantRepository.find(text, offset, size);
        return ResponseEntity.ok(restaurantList);
    }

    @GetMapping(params = {"category", "tag"})
    public ResponseEntity<List<Restaurant>> findByCategoryAndTag(@RequestParam(name = "category") CategoriesEnum category, @RequestParam(name = "tag") TagsEnum tag) {
        List<Restaurant> restaurantList = this.restaurantService.findByCategoryAndTag(category, tag);
        return restaurantList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(restaurantList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable String id) {
        Restaurant restaurant = restaurantRepository.findOne(id);
        return restaurant == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(restaurant);
    }

    @GetMapping("{id}/dishes")
    public List<Dish> getDishesFromRestaurant(@PathVariable String restaurantId) {
        return this.dishService.findByRestaurantId(restaurantId);
    }

    @GetMapping(params = "customerId")
    public ResponseEntity<List<Restaurant>> findCustomerFavoriteRestaurant(@RequestParam(name = "customerId") String customerId) {
        List<Restaurant> restaurantList = restaurantRepository.findFavoriteByCustomerId(customerId);
        return restaurantList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(restaurantList);
    }
}
