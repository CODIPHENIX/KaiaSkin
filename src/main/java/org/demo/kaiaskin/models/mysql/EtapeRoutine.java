package org.demo.kaiaskin.models.mysql;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "etaperoutines")
public class EtapeRoutine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_routine")
    private Routine routines;

    private byte ordre;

    @ManyToOne
    @JoinColumn(name = "id_produit")
    private Produit produitsEr;

}
