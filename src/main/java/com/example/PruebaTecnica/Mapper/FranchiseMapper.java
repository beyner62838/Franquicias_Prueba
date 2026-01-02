package com.example.PruebaTecnica.Mapper;

import com.example.PruebaTecnica.Dto.ProductResponse;
import com.example.PruebaTecnica.Entity.Product;

public class FranchiseMapper {
    private FranchiseMapper() {}

    public static ProductResponse toProductResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getStock());
    }
}
