package com.kgregorczyk.store_example.category;

import com.kgregorczyk.store_example.library.clients.ProductFeignClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CategoryApplication {

  public static void main(String[] args) {
    SpringApplication.run(CategoryApplication.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public ProductFeignClient categoryClient(@Value("${store.product-service-host}") String url) {
    return ProductFeignClient.createClient(url);
  }
}
