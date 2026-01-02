package com.example.PruebaTecnica.Mapper;

import com.example.PruebaTecnica.Entity.Branch;

public class BranchMapper {
    private BranchMapper() {}

    public static String branchLabel(Branch branch) {
        return branch.getId() + " - " + branch.getName();
    }
}
