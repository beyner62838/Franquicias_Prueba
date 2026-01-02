package com.example.PruebaTecnica.Repository;

import com.example.PruebaTecnica.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository <Product, Long> {
    Optional<Product> findTopByBranchIdOrderByStockDesc(Long branchId);
}
