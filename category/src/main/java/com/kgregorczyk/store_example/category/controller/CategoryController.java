package com.kgregorczyk.store_example.category.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.kgregorczyk.store_example.category.model.Category;
import com.kgregorczyk.store_example.category.repository.CategoryRepository;
import com.kgregorczyk.store_example.library.clients.ProductFeignClient;
import com.kgregorczyk.store_example.library.dto.CreateCategory;
import com.kgregorczyk.store_example.library.dto.GetCategory;
import com.kgregorczyk.store_example.library.dto.GetCategoryWithProducts;
import com.kgregorczyk.store_example.library.dto.GetProduct;
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
public class CategoryController {

  private final CategoryRepository categoryRepository;
  private final ProductFeignClient productFeignClient;
  private final ModelMapper modelMapper;

  @Autowired
  public CategoryController(
      CategoryRepository categoryRepository, ProductFeignClient productFeignClient,
      ModelMapper modelMapper) {
    this.categoryRepository = categoryRepository;
    this.productFeignClient = productFeignClient;
    this.modelMapper = modelMapper;
  }

  @GetMapping("/")
  public Flux<GetCategory> getAllCategories() {
    return categoryRepository.findAll()
        .map(categoryFromDb -> modelMapper.map(categoryFromDb, GetCategory.class));
  }

  @PostMapping("/")
  public Mono<GetCategory> createCategory(@Valid @RequestBody CreateCategory category) {
    return categoryRepository.save(modelMapper.map(category, Category.class))
        .map(categoryFromDb -> modelMapper.map(categoryFromDb, GetCategory.class));
  }

  @GetMapping("/{id:[a-f\\d]{24}}")
  public Mono<ResponseEntity<GetCategory>> getCategory(@PathVariable String id) {
    return categoryRepository.findById(id)
        .map(categoryFromDb -> ok(modelMapper.map(categoryFromDb, GetCategory.class)))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/{id:[a-f\\d]{24}}/exists")
  public Mono<Boolean> categoryExists(@PathVariable String id) {
    return categoryRepository.existsById(id);
  }

  @GetMapping("/{id:[a-f\\d]{24}}/with-products")
  public Mono<GetCategoryWithProducts> getCategoryWithProducts(
      @PathVariable String id) {
    Flux<GetProduct> productsFlux = productFeignClient
        .getProductsForCategoryId(id);
    Mono<GetCategoryWithProducts> category = categoryRepository.findById(id)
        .map(categoryFromDb -> modelMapper.map(categoryFromDb, GetCategoryWithProducts.class));

    return productsFlux.collectList().flatMap(products -> category.map(categoryWithProducts -> {
      categoryWithProducts.setProducts(products);
      return categoryWithProducts;
    }));
  }

  @DeleteMapping("/{id:[a-f\\d]{24}}")
  public Mono<Void> deleteCategory(@PathVariable String id) {
    return categoryRepository.deleteById(id);
  }

}
