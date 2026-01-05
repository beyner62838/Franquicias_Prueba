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

  
    @GetMapping("/{franchiseId}/top-products")
    public ResponseEntity<List<TopProductResponse>> topProductsByBranch(@PathVariable Long franchiseId) {
        Franchise franchise = franchiseService.getById(franchiseId);

        
        List<TopProductResponse> result = franchise.getBranches().stream()
                .map(branch -> productService.getTopStockByBranch(branch.getId()))
                .filter(p -> p != null) // skip branches with no products
                .map(ProductMapper::toTopResponse)
                .toList();

        return ResponseEntity.ok(result);
    }
}
