package com.example.PruebaTecnica.Mapper;

import com.example.PruebaTecnica.Dto.CreateProductRequest;
import com.example.PruebaTecnica.Dto.ProductResponse;
import com.example.PruebaTecnica.Dto.TopProductResponse;
import com.example.PruebaTecnica.Entity.Branch;
import com.example.PruebaTecnica.Entity.Product;

/**
 * Manual mapper utilities for Product.
 *
 * Why manual mapping?
 * - Easy to read in technical tests.
 * - No extra dependencies (like MapStruct) needed.
 */
public class ProductMapper {

    private ProductMapper() {
        // Utility class: prevent instantiation
    }

    /**
     * Maps CreateProductRequest DTO + Branch into a Product entity.
     */
    public static Product toEntity(CreateProductRequest dto, Branch branch) {
        return Product.builder()
                .name(dto.getName())
                .stock(dto.getStock())
                .branch(branch)
                .build();
    }

    /**
     * Maps Product entity into a simple response DTO.
     */
    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getStock());
    }

    /**
     * Maps Product into the "top product per branch" response DTO,
     * including the branch identification required by the statement.
     */
    public static TopProductResponse toTopResponse(Product product) {
        return new TopProductResponse(
                product.getBranch().getId(),
                product.getBranch().getName(),
                product.getId(),
                product.getName(),
                product.getStock()
        );
    }
}
