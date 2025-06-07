package org.demo.kaiaskin.models.mysql;

import jakarta.persistence.*;
import lombok.Data;
import org.demo.kaiaskin.models.mysql.enums.Moment;

import java.util.List;

@Data
@Entity
@Table(name = "routines")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private Moment typeMoment;

    @OneToMany(mappedBy = "routines")
    private List<EtapeRoutine> etaperoutines;

    @ManyToMany
    @JoinTable(
            name = "routine_diagnostic",
            joinColumns = @JoinColumn(name = "id_routine"),
            inverseJoinColumns = @JoinColumn(name = "id_diagnostic")
    )
    private List<Diagnostic> diagnostics;


}
