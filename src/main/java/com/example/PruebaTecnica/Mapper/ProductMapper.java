package com.example.PruebaTecnica.Mapper;

import com.example.PruebaTecnica.Dto.CreateProductRequest;
import com.example.PruebaTecnica.Dto.TopProductResponse;
import com.example.PruebaTecnica.Entity.Branch;
import com.example.PruebaTecnica.Entity.Product;
import lombok.RequiredArgsConstructor;

public class ProductMapper {

    private ProductMapper() {}

    // DTO -> Entity
    public static Product toEntity(CreateProductRequest dto, Branch branch) {
        return Product.builder()
                .name(dto.getName())
                .stock(dto.getStock())
                .branch(branch)
                .build();
    }

    // Entity -> DTO (top stock)
    public static TopProductResponse toTopResponse(Product product) {
        return new TopProductResponse(
                product.getBranch().getName(),
                product.getName(),
                product.getStock()
        );
    }
}
