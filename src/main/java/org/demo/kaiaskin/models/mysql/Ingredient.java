package org.demo.kaiaskin.models.mysql;

import jakarta.persistence.*;
import lombok.Data;
import org.demo.kaiaskin.models.mysql.enums.TypeIngredient;

import java.util.List;

@Data
@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nom;
    @Column(columnDefinition = "TEXT")
    private String effect;
    @Enumerated(EnumType.STRING)
    private TypeIngredient type;
    private boolean allergene;

    @ManyToMany
    @JoinTable(
            name = "ingredient_diagnostic",
            joinColumns = @JoinColumn(name = "id_ingredient"),
            inverseJoinColumns = @JoinColumn(name = "id_diagnostic")
    )
    private List<Diagnostic> diagnostics;

    @ManyToMany(mappedBy = "ingredients")
    private List<Produit> produits;

}
