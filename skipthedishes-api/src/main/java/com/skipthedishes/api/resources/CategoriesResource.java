package com.skipthedishes.api.resources;

import com.skipthedishes.api.entities.CategoriesEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoriesResource {

    @GetMapping
    public ResponseEntity<CategoriesEnum[]> findCategories() {
        return ResponseEntity.ok(CategoriesEnum.values());
    }

}
