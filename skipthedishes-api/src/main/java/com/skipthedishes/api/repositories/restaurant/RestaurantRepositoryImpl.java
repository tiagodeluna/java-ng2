package com.skipthedishes.api.repositories.restaurant;

import com.skipthedishes.api.entities.CategoriesEnum;
import com.skipthedishes.api.entities.Restaurant;
import com.skipthedishes.api.entities.TagsEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collections;
import java.util.List;

public class RestaurantRepositoryImpl implements RestaurantRepositoryQuery {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Restaurant> find(String text,int offset, int size) {
        final Criteria criteriaDefinition = new Criteria();

        criteriaDefinition.orOperator(
                Criteria.where("name").regex(text, "i"),
                Criteria.where("address").regex(text, "i"),
                Criteria.where("timeToWait").regex(text, "i"),
                Criteria.where("deliveryFee").regex(text, "i")
        );

        Query query = Query.query(criteriaDefinition);
        query.skip(offset);
        query.limit(size);
        query.fields().exclude("dishes");

        return mongoTemplate.find(query, Restaurant.class);
    }

    @Override
    public List<Restaurant> findByCategoryAndTag(CategoriesEnum category, TagsEnum tag) {
        Restaurant example = new Restaurant();
        example.setCategories(Collections.singleton(category));
        if (tag != null) {
            example.setTags(Collections.singleton(tag));
        }

        Query query = Query.query(Criteria.byExample(example));
        query.fields().exclude("dishes");

        return mongoTemplate.find(query, Restaurant.class);
    }

}
