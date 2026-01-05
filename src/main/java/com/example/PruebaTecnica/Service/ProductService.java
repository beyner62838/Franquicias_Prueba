package com.example.PruebaTecnica.Service;

import com.example.PruebaTecnica.Dto.CreateProductRequest;
import com.example.PruebaTecnica.Entity.Branch;
import com.example.PruebaTecnica.Entity.Product;
import com.example.PruebaTecnica.Exception.NotFoundException;
import com.example.PruebaTecnica.Mapper.ProductMapper;
import com.example.PruebaTecnica.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final BranchService branchService;

    public Product create(Long branchId, CreateProductRequest request) {
      
        Branch branch = branchService.getById(branchId);

        
        Product product = ProductMapper.toEntity(request, branch);

        return repository.save(product);
    }

    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    
    public Product updateName(Long id, String newName) {
        Product product = getById(id);
        product.setName(newName);
        return repository.save(product);
    }

    
    public Product updateStock(Long id, Integer newStock) {
        Product product = getById(id);
        product.setStock(newStock);
        return repository.save(product);
    }

    
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Product not found");
        }
        repository.deleteById(id);
    }


    public Product getTopStockByBranch(Long branchId) {
        return repository.findTopByBranchIdOrderByStockDesc(branchId)
                .orElse(null);
    }
}
