package com.example.Prueba_Tecnica.Entity;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name product is required")
    @Column(nullable = false, unique = true)
    private String name;

    @Min(value =0 , message = "stock cannot be negative")
    @Column(nullable = false)
    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Branch_id" , nullable = false)
    private Branch branch;

}
