package com.feirinha.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ItemDTO {
  @NotBlank
  private String name;
  @NotNull
  @PositiveOrZero
  private int quantity;
}