package org.demo.kaiaskin.models.mysql;

import jakarta.persistence.*;
import lombok.Data;
import org.demo.kaiaskin.models.mysql.enums.Roles;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "utilisateur")
public class Utilisateur implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String motDePasse;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles role;
    private boolean estSupprimer = false;
    private LocalDateTime dateSuppression;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;

     @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Diagnostic> diagnostics;
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Avis> avisList;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return motDePasse;
    }

    @Override
    public String getUsername() {
        // email in our case
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
