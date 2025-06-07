package org.demo.kaiaskin.models.mysql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "problems")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @ManyToMany
    @JoinTable(
            name = "problem_diagnostic",
            joinColumns = @JoinColumn(name = "id_problem"),
            inverseJoinColumns = @JoinColumn(name = "id_diagnostic")
    )
    private List<Diagnostic> diagnostics;

    @ManyToMany
    @JoinTable(
            name = "problem_produit",
            joinColumns = @JoinColumn(name = "id_problem"),
            inverseJoinColumns = @JoinColumn(name = "id_produit")
    )
    private List<Produit> produitList;

    @ManyToMany(mappedBy = "problems")
    private List<Avis> avisList;

}
