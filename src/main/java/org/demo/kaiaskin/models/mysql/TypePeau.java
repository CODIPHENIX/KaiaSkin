package org.demo.kaiaskin.models.mysql;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "typespeau")
public class TypePeau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;

    @OneToMany(mappedBy = "typesPeau")
    private List<Diagnostic> diagnostics;

    @ManyToMany
    @JoinTable(
            name = "typepeau_produit",
            joinColumns = @JoinColumn(name = "id_typepeau"),
            inverseJoinColumns = @JoinColumn(name = "id_produit")
    )
    private List<Produit> produitsList;
}
