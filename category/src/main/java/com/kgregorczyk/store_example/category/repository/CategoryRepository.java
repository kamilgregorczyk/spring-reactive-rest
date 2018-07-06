package com.kgregorczyk.store_example.category.repository;

import com.kgregorczyk.store_example.category.model.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {

}
