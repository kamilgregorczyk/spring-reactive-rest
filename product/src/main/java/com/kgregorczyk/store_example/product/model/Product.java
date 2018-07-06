package com.kgregorczyk.store_example.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Product {

  @Id
  @JsonProperty(access = Access.READ_ONLY)
  private String id;

  @Indexed
  @NotEmpty
  @NotBlank
  private String title;

  @Min(0)
  private float price;

  @NotBlank
  @NotEmpty
  @Pattern(regexp = "[a-f\\d]{24}")
  private String categoryId;
}
