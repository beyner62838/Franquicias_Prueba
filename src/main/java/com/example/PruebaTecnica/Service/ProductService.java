package com.example.PruebaTecnica.Service;

import com.example.PruebaTecnica.Entity.Branch;
import com.example.PruebaTecnica.Entity.Product;
import com.example.PruebaTecnica.Repository.ProductRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository repository;
    private final BranchService branchService;

    public Product create (Long branchId, String name, Integer stock ){
        Branch branch = branchService.getById(branchId);
        Product product = Product.builder()
                .name(name)
                .stock(stock)
                .branch(branch)
                .build();
        return repository.save(product);
    }

    public Product updateStock (Long id , Integer newStock){
        Product Product = getById(id);
        Product.setStock(newStock);
        return repository.save(Product);
    }

    public Product getById(Long id ){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void delete(Long id) {
            repository.deleteById(id);
    }


}
