package com.kgregorczyk.store_example.library.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategory {

  @JsonProperty(access = Access.READ_ONLY)
  private String id;

  @NotEmpty
  @NotBlank
  private String title;
}

