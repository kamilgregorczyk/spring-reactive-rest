package com.kgregorczyk.store_example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProduct {

  private String id;
  private String title;
  private float price;
  private String categoryId;

}
