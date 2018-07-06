package com.kgregorczyk.store_example.product.repository;

import com.kgregorczyk.store_example.product.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

  Flux<Product> findByCategoryId(String categoryId);

}
