package com.feirinha.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ItemDTO {
  @NotBlank(message = "Name is required")
  private String name;
  @NotNull(message = "Quantity is required")
  @PositiveOrZero(message = "Quantity must be greater than or equal to 0")
  private int quantity;
}