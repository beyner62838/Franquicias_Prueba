package com.example.PruebaTecnica.Controller;

import com.example.PruebaTecnica.Dto.CreateProductRequest;
import com.example.PruebaTecnica.Dto.UpdateStockRequest;
import com.example.PruebaTecnica.Entity.Product;
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

    // 1) Agregar producto a una sucursal
    @PostMapping("/branches/{branchId}/products")
    public ResponseEntity<Product> createProduct(
            @PathVariable Long branchId,
            @Valid @RequestBody CreateProductRequest request
    ) {
        Product product = productService.create(
                branchId,
                request.getName(),
                request.getStock()
        );
        return ResponseEntity.ok(product);
    }

    // 2) Actualizar stock de un producto
    @PutMapping("/products/{productId}/stock")
    public ResponseEntity<Product> updateStock(
            @PathVariable Long productId,
            @Valid @RequestBody UpdateStockRequest request
    ) {
        Product updated = productService.updateStock(productId, request.getStock());
        return ResponseEntity.ok(updated);
    }

    // 3) Eliminar producto
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.delete(productId);
        return ResponseEntity.noContent().build();
    }
}
