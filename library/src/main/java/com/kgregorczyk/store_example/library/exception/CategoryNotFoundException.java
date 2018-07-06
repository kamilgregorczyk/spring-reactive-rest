package com.kgregorczyk.store_example.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException {

  public CategoryNotFoundException(String productId) {
    super(String.format("Category of id %s was not found!", productId));
  }
}
