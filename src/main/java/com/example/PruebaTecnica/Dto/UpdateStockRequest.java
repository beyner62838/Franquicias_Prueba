package com.example.PruebaTecnica.Dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateStockRequest {

    @Min(value = 0 ,message = "tock must be zero or greater")
    private Integer stock;
}
