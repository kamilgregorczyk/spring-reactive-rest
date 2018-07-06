package com.kgregorczyk.store_example.product;

import com.kgregorczyk.store_example.library.clients.CategoryFeignClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProductApplication.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public CategoryFeignClient categoryClient(@Value("${store.category-service-host}") String url) {
    return CategoryFeignClient.createClient(url);
  }
}
