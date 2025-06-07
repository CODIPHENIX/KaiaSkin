package org.demo.kaiaskin.models.mysql;

import jakarta.persistence.*;
import lombok.Data;
import org.demo.kaiaskin.models.mysql.enums.PrefRoutine;
import org.demo.kaiaskin.models.mysql.enums.Type_Zone;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "diagnostics")
public class Diagnostic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type_Zone zones;

    @Column(nullable = false)
    private boolean estSensitble;
    @Column(nullable = false)
    private boolean estAcneique;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PrefRoutine PrefRoutine;

    private String questionnaireMongoId;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime dateRealiser;

   @ManyToOne
    @JoinColumn(name = "id_utilisateur",nullable = false)
    private Utilisateur utilisateur;


    @ManyToMany(mappedBy = "diagnostics")
    private List<Ingredient> ingredients;

    @ManyToOne
    @JoinColumn(name = "id_typepeau",nullable = false)
    private TypePeau typesPeau;

    @ManyToMany(mappedBy = "diagnostics")
    private List<Routine> routines;

    @ManyToMany(mappedBy = "diagnostics")
    private List<Problem> problems;






}
