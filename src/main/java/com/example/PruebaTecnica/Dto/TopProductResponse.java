package com.example.PruebaTecnica.Dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TopProductResponse {
    private String branchName;
    private String name;
    private Integer stock;



}
