package org.demo.kaiaskin.repositories.mysql;

import org.demo.kaiaskin.models.mysql.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository
        extends JpaRepository<Utilisateur, Long> {
    @Query("select u from Utilisateur u where size(u.diagnostics)>0 ")
    List<Utilisateur> findUserWithAtLeastOneDiagnostic();

    Optional<Utilisateur> findByEmail(String email);
    boolean existsByEmail(String email);
    List<Utilisateur> findAllByNomIgnoreCase(String nom);

    List<Utilisateur> findAllByNomContainingIgnoreCase(String nom);
    List<Utilisateur> findAllByEstSupprimerIsFalse();
    List<Utilisateur> findAllByEstSupprimerTrueAndDateSuppressionBefore(LocalDateTime date);
}
