package com.example.Prueba_Tecnica.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "branches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is required")
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @JsonBackReference
    @OneToMany(
            mappedBy = "branches",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Product> product = new ArrayList<>();

}