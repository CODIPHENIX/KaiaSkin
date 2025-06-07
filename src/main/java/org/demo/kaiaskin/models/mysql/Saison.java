package org.demo.kaiaskin.models.mysql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "saison")
public class Saison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nom;

   @ManyToMany
    @JoinTable(
            name = "produit_saison",
            joinColumns = @JoinColumn(name = "id_saison"),
            inverseJoinColumns = @JoinColumn(name = "id_produit")
    )
    private List<Produit> produitList;


}
