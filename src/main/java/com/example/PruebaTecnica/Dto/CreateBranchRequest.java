package com.example.PruebaTecnica.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateBranchRequest {
    @NotBlank(message = "name is required")
    private String name;
}
