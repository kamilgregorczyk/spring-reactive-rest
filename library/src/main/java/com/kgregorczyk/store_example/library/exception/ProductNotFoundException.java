package com.kgregorczyk.store_example.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(String productId) {
    super(String.format("Product of id %s was not found!", productId));
  }
}
