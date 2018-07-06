package com.kgregorczyk.store_example.library.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProduct {

  @JsonProperty(access = Access.READ_ONLY)
  private String id;

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
