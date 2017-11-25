package com.skipthedishes.api.resources;


import com.skipthedishes.api.entities.TagsEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
public class TagsResource {

    @GetMapping
    public ResponseEntity<TagsEnum[]> findTags() {
        return ResponseEntity.ok(TagsEnum.values());
    }
}
