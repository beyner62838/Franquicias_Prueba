package com.example.PruebaTecnica.Controller;

import com.example.PruebaTecnica.Dto.CreateProductRequest;
import com.example.PruebaTecnica.Dto.ProductResponse;
import com.example.PruebaTecnica.Dto.UpdateNameRequest;
import com.example.PruebaTecnica.Dto.UpdateStockRequest;
import com.example.PruebaTecnica.Entity.Product;
import com.example.PruebaTecnica.Mapper.ProductMapper;
import com.example.PruebaTecnica.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    
    @PostMapping("/branches/{branchId}/products")
    public ResponseEntity<ProductResponse> createProduct(
            @PathVariable Long branchId,
            @Valid @RequestBody CreateProductRequest request
    ) {
        Product product = productService.create(branchId, request);
        return ResponseEntity.ok(ProductMapper.toResponse(product));
    }

  
    @PutMapping("/products/{productId}/stock")
    public ResponseEntity<ProductResponse> updateStock(
            @PathVariable Long productId,
            @Valid @RequestBody UpdateStockRequest request
    ) {
        Product updated = productService.updateStock(productId, request.getStock());
        return ResponseEntity.ok(ProductMapper.toResponse(updated));
    }

   
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.delete(productId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/products/{productId}/name")
    public ResponseEntity<ProductResponse> updateProductName(
            @PathVariable Long productId,
            @Valid @RequestBody UpdateNameRequest request
    ) {
        Product updated = productService.updateName(productId, request.getName());
        return ResponseEntity.ok(ProductMapper.toResponse(updated));
    }
}
