package com.example.PruebaTecnica.Service;

import com.example.PruebaTecnica.Entity.Franchise;
import com.example.PruebaTecnica.Repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FranchiseService {

    private final FranchiseRepository repository;

    public Franchise create (String name){
        Franchise franchise = Franchise.builder()
                .name(name)
                .build();
        return repository.save(franchise);
    }

    public Franchise getById(Long id ){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Franchise not found"));
    }

    public Franchise updateName (Long id , String newName){
        Franchise franchise = getById(id);
        franchise.setName(newName);
        return repository.save(franchise);
    }


}
