package org.demo.kaiaskin.models.mysql;

import jakarta.persistence.*;
import lombok.Data;
import org.demo.kaiaskin.models.mysql.enums.Categorie_P;
import org.demo.kaiaskin.models.mysql.enums.Type_Produit;

import java.util.List;

@Data
@Entity
@Table(name = "produits")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nom;

    private String marque;
    @Enumerated(EnumType.STRING)
    private Type_Produit type;

    @Enumerated(EnumType.STRING)
    private Categorie_P categorieP;
    private double note;
    private boolean naturel;
    private String urlImage;
    private String urlProduit;

    @OneToMany(mappedBy= "produit")
    private List<Avis> avis;


    @ManyToMany
    @JoinTable(
            name = "ingredient_produit",
            joinColumns = @JoinColumn(name = "id_produit"),
            inverseJoinColumns = @JoinColumn(name = "id_ingredient")
    )
    private List<Ingredient> ingredients;

    @ManyToMany(mappedBy = "produitList")
    private List<Saison> saison;

    @OneToMany(mappedBy = "produitsEr")
    private List<EtapeRoutine> etapesroutine;

    @ManyToMany(mappedBy = "produitsList")
    private List<TypePeau> typePeauList;

    @ManyToMany(mappedBy = "produitList")
    private List<Problem> problemList;

}
