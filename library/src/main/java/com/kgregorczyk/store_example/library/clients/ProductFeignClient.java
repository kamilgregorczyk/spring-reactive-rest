package com.kgregorczyk.store_example.library.clients;

import com.kgregorczyk.store_example.library.dto.GetProduct;
import feign.Param;
import feign.RequestLine;
import reactivefeign.ReactiveFeign;
import reactor.core.publisher.Flux;

public interface ProductFeignClient {

  static ProductFeignClient createClient(String productServiceUrl) {
    return ReactiveFeign.<ProductFeignClient>builder()
        .target(ProductFeignClient.class, productServiceUrl);
  }

  @RequestLine("GET /category/{categoryId}")
  Flux<GetProduct> getProductsForCategoryId(@Param("categoryId") String categoryId);
}
