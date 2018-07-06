package com.kgregorczyk.store_example.product.controller;

import com.kgregorczyk.store_example.library.clients.CategoryFeignClient;
import com.kgregorczyk.store_example.library.dto.CreateProduct;
import com.kgregorczyk.store_example.library.dto.GetProduct;
import com.kgregorczyk.store_example.library.exception.CategoryNotFoundException;
import com.kgregorczyk.store_example.product.model.Product;
import com.kgregorczyk.store_example.product.repository.ProductRepository;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductController {

  private final ProductRepository productRepository;
  private final ModelMapper modelMapper;
  private CategoryFeignClient categoryClient;

  @Autowired
  public ProductController(
      ProductRepository productRepository, ModelMapper modelMapper,
      CategoryFeignClient categoryClient) {
    this.productRepository = productRepository;
    this.modelMapper = modelMapper;
    this.categoryClient = categoryClient;
  }

  @GetMapping("/")
  public Flux<GetProduct> getAllProducts() {
    return productRepository.findAll()
        .map(productFromDb -> modelMapper.map(productFromDb, GetProduct.class));
  }

  @GetMapping("/category/{categoryId:[a-f\\d]{24}}")
  public Flux<GetProduct> getProductsForCategory(@PathVariable String categoryId) {
    return productRepository.findByCategoryId(categoryId)
        .map(productFromDb -> modelMapper.map(productFromDb, GetProduct.class));
  }

  @PostMapping("/")
  public Mono<GetProduct> createProduct(@Valid @RequestBody CreateProduct product) {
    return categoryClient.categoryExists(product.getCategoryId()).flatMap(exists -> {
      if (exists) {
        return productRepository.save(modelMapper.map(product, Product.class))
            .map(productFromDb -> modelMapper.map(productFromDb, GetProduct.class));
      }
      throw new CategoryNotFoundException(product.getCategoryId());
    });
  }

  @GetMapping("/{id:[a-f\\d]{24}}")
  public Mono<ResponseEntity<GetProduct>> getProduct(@PathVariable String id) {
    return productRepository.findById(id)
        .map(productFromDb -> ResponseEntity.ok(modelMapper.map(productFromDb, GetProduct.class)))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id:[a-f\\d]{24}}")
  public Mono<Void> deleteProduct(@PathVariable String id) {
    return productRepository.deleteById(id);
  }
}
