package com.skipthedishes.api.resources;

import com.skipthedishes.api.entities.CategoriesEnum;
import com.skipthedishes.api.entities.Dish;
import com.skipthedishes.api.entities.TagsEnum;
import com.skipthedishes.api.repositories.DishRepository;
import com.skipthedishes.api.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/dishes")
public class DishResource {
    
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private DishService dishService;

    @PostMapping
    public ResponseEntity<Dish> create(@Valid @RequestBody Dish dish) {
        Dish dishSaved = dishRepository.save(dish);
        return ResponseEntity.status(HttpStatus.CREATED).body(dishSaved);
    }

    @PostMapping(path = "bulk")
    public ResponseEntity<List<Dish>> createBulk(@RequestBody List<Dish> dishList) {
        dishList.stream().forEach(dishRepository::save);
        return ResponseEntity.status(HttpStatus.CREATED).body(dishList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dish> update(@PathVariable String id, @Valid @RequestBody Dish dish) {
        return ResponseEntity.ok(dishService.update(id, dish));
    }

    @PutMapping(path = "bulk")
    public ResponseEntity<List<Dish>> updateBulk(@RequestBody List<Dish> dishList) {
        dishList.stream().forEach(r -> dishService.update(r.getId(), r));
        return ResponseEntity.status(HttpStatus.CREATED).body(dishList);
    }

    @GetMapping
    public ResponseEntity<List<Dish>> findAll() {
        List<Dish> dishList = dishRepository.findAll();
        return ResponseEntity.ok(dishList);
    }

    @GetMapping(params = {"text","offset","size"})
    public ResponseEntity<List<Dish>> find(@RequestParam(name = "text") String text, @RequestParam(name = "offset")int offset,@RequestParam(name = "size")int size) {
        List<Dish> dishList = dishRepository.find(text,offset,size);
        return  ResponseEntity.ok(dishList);
    }

    @GetMapping(params = "customerId")
    public ResponseEntity<List<Dish>> findCustomerFavoriteDish(@RequestParam(name = "customerId") String customerId) {
        List<Dish> dishList = dishRepository.findFavoriteByCustomerId(customerId);
        return dishList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(dishList);
    }

    @GetMapping(params = {"restaurantId","category","tag"})
    public ResponseEntity<List<Dish>> findByCategoryAndTag(@RequestParam(name = "restaurantId")String restaurantId,
                                                     @RequestParam(name = "category")CategoriesEnum category,
                                                     @RequestParam(name = "tag")TagsEnum tag) {
        List<Dish> restaurantList = this.dishService.findByCategoryAndTag(restaurantId, category, tag);
        return restaurantList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(restaurantList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> findById(@PathVariable String id) {
        Dish dish = dishService.findById(id);
        return dish == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dish);
    }

}
