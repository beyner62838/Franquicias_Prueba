package com.example.PruebaTecnica.Controller;

import com.example.PruebaTecnica.Dto.CreateBranchRequest;
import com.example.PruebaTecnica.Dto.CreateFranchiseRequest;
import com.example.PruebaTecnica.Dto.TopProductResponse;
import com.example.PruebaTecnica.Dto.UpdateNameRequest;
import com.example.PruebaTecnica.Entity.Franchise;
import com.example.PruebaTecnica.Mapper.ProductMapper;
import com.example.PruebaTecnica.Service.BranchService;
import com.example.PruebaTecnica.Service.FranchiseService;
import com.example.PruebaTecnica.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Franchise endpoints.
 *
 * Responsibilities:
 * - Create a franchise.
 * - Add branches to an existing franchise.
 * - Return "top stock products" per branch for a given franchise (required by the statement).
 *
 * Notes:
 * - Business rules live in services; controllers only orchestrate HTTP.
 * - DTOs are used as request/response models to avoid exposing entities directly.
 */
@RestController
@RequestMapping("/api/franchises")
@RequiredArgsConstructor
public class FranchiseController {

    private final FranchiseService franchiseService;
    private final BranchService branchService;
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Franchise> create(@Valid @RequestBody CreateFranchiseRequest request) {
        return ResponseEntity.ok(franchiseService.create(request.getName()));
    }

    @PostMapping("/{franchiseId}/branches")
    public ResponseEntity<?> addBranch(
            @PathVariable Long franchiseId,
            @Valid @RequestBody CreateBranchRequest req
    ) {
        return ResponseEntity.ok(branchService.create   (franchiseId, req.getName()));
    }

    @PutMapping("/{franchiseId}/name")
    public ResponseEntity<Franchise> updateFranchiseName(
            @PathVariable Long franchiseId,
            @Valid @RequestBody UpdateNameRequest request
    ) {
        return ResponseEntity.ok(franchiseService.updateName(franchiseId, request.getName()));
    }

    /**
     * Requirement #7: For a given franchise, return the product with the highest stock for each branch.
     *
     * GET /api/franchises/{franchiseId}/top-products
     *
     * Response: list of products with branch info (branchId/branchName) included.
     */
    @GetMapping("/{franchiseId}/top-products")
    public ResponseEntity<List<TopProductResponse>> topProductsByBranch(@PathVariable Long franchiseId) {
        Franchise franchise = franchiseService.getById(franchiseId);

        // For each branch, fetch its top-stock product, then map it to a response DTO.
        List<TopProductResponse> result = franchise.getBranches().stream()
                .map(branch -> productService.getTopStockByBranch(branch.getId()))
                .filter(p -> p != null) // skip branches with no products
                .map(ProductMapper::toTopResponse)
                .toList();

        return ResponseEntity.ok(result);
    }
}
