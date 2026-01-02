package com.example.PruebaTecnica.Controller;

import com.example.PruebaTecnica.Dto.UpdateNameRequest;
import com.example.PruebaTecnica.Entity.Branch;
import com.example.PruebaTecnica.Service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @PutMapping("/{branchId}/name")
    public ResponseEntity<Branch> updateBranchName(
            @PathVariable Long branchId,
            @Valid @RequestBody UpdateNameRequest request
    ) {
        return ResponseEntity.ok(branchService.updateName(branchId, request.getName()));
    }
}
