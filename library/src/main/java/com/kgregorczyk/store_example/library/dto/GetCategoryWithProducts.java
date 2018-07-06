package com.kgregorczyk.store_example.library.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoryWithProducts {

  private String id;
  private String title;
  private List<GetProduct> products;
}

