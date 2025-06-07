package org.demo.kaiaskin.models.mysql;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "avis")
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nom;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur",nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_produit",nullable = false)
    private Produit produit;

    @Column(nullable = false)
    private float note;
    @Column(columnDefinition = "TEXT")
    private String commentaire;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime date_creation;
    private LocalDateTime date_modification;

    @ManyToMany
    @JoinTable(
            name = "prlem_avis",
            joinColumns = @JoinColumn(name = "id_avis"),
            inverseJoinColumns = @JoinColumn(name = "id_problem")
    )
    private List<Problem> problems;
}
