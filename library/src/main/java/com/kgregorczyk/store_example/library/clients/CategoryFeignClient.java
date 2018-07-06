package com.kgregorczyk.store_example.library.clients;

import feign.Param;
import feign.RequestLine;
import reactivefeign.ReactiveFeign;
import reactor.core.publisher.Mono;

public interface CategoryFeignClient {

  static CategoryFeignClient createClient(String categoryServiceUrl) {
    return ReactiveFeign.<CategoryFeignClient>builder()
        .target(CategoryFeignClient.class, categoryServiceUrl);
  }

  @RequestLine("GET /{categoryId}/exists")
  Mono<Boolean> categoryExists(@Param("categoryId") String categoryId);
}
