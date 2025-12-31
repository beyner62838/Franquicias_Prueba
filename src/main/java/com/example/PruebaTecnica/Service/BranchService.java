package com.example.PruebaTecnica.Service;

import com.example.PruebaTecnica.Entity.Branch;
import com.example.PruebaTecnica.Entity.Franchise;
import com.example.PruebaTecnica.Repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BranchService {
    private final BranchRepository repository;
    private final FranchiseService franchiseService;

    public Branch create (long franchiseId ,String name){
        Franchise franchise = franchiseService.getById(franchiseId);
        Branch branch = Branch.builder()
                .name(name)
                .franchise(franchise)
                .build();
        return repository.save(branch);
    }

    public Branch getById(Long id ){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public Branch updateName (Long id , String newName){
        Branch branch = getById(id);
        branch.setName(newName);
        return repository.save(branch);
    }
}
