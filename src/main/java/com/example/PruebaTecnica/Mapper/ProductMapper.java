package com.example.PruebaTecnica.Mapper;

import com.example.PruebaTecnica.Dto.CreateProductRequest;
import com.example.PruebaTecnica.Dto.ProductResponse;
import com.example.PruebaTecnica.Dto.TopProductResponse;
import com.example.PruebaTecnica.Entity.Branch;
import com.example.PruebaTecnica.Entity.Product;


public class ProductMapper {

    private ProductMapper() {
     
    }

    public static Product toEntity(CreateProductRequest dto, Branch branch) {
        return Product.builder()
                .name(dto.getName())
                .stock(dto.getStock())
                .branch(branch)
                .build();
    }

  
    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getStock());
    }

   
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
