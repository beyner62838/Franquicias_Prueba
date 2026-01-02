package com.example.PruebaTecnica.Service;

import com.example.PruebaTecnica.Dto.CreateProductRequest;
import com.example.PruebaTecnica.Entity.Branch;
import com.example.PruebaTecnica.Entity.Product;
import com.example.PruebaTecnica.Exception.NotFoundException;
import com.example.PruebaTecnica.Mapper.ProductMapper;
import com.example.PruebaTecnica.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Product business logic.
 *
 * Statement requirements:
 * - Add product to a branch.
 * - Update product stock.
 * - Delete product.
 * - Query the product with the highest stock for a given branch (used to build the franchise report).
 *
 * Notes:
 * - Product belongs to a Branch (mandatory relationship).
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final BranchService branchService;

    /**
     * Requirement #4: Add a product to a branch.
     *
     * @param branchId branch id
     * @param request DTO containing product name and stock
     * @return persisted product
     */
    public Product create(Long branchId, CreateProductRequest request) {
        // Ensure branch exists and load it to set the relationship.
        Branch branch = branchService.getById(branchId);

        // Convert request DTO into entity.
        Product product = ProductMapper.toEntity(request, branch);

        return repository.save(product);
    }

    /**
     * Finds a product by id.
     */
    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    /**
     * Extra points: Update product name.
     */
    public Product updateName(Long id, String newName) {
        Product product = getById(id);
        product.setName(newName);
        return repository.save(product);
    }

    /**
     * Requirement #6: Update product stock.
     */
    public Product updateStock(Long id, Integer newStock) {
        Product product = getById(id);
        product.setStock(newStock);
        return repository.save(product);
    }

    /**
     * Requirement #5: Delete product.
     *
     * We check existence first to return a clean 404 instead of a generic exception.
     */
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Product not found");
        }
        repository.deleteById(id);
    }

    /**
     * Returns the product with the highest stock for a specific branch.
     *
     * Used by the franchise endpoint:
     * GET /api/franchises/{franchiseId}/top-products
     *
     * @param branchId branch id
     * @return top product or null if branch has no products
     */
    public Product getTopStockByBranch(Long branchId) {
        return repository.findTopByBranchIdOrderByStockDesc(branchId)
                .orElse(null);
    }
}
