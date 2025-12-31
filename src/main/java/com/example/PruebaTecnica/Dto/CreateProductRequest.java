package com.example.PruebaTecnica.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateProductRequest {

    @NotBlank(message = "product name is required")
    private String name;

    @Min(value = 0, message = "stock must be zero or greater")
    private Integer stock;
}
