package org.demo.kaiaskin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.demo.kaiaskin.models.mysql.Utilisateur;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String role;
    private String token;
    public UserDto(Utilisateur u,String token) {
        this.id = u.getId();
        this.nom = u.getNom();
        this.prenom = u.getPrenom();
        this.email = u.getEmail();
        this.role = u.getRole().toString();
        this.token = token;
    }
    public UserDto(Utilisateur u) {
        this.id = u.getId();
        this.nom = u.getNom();
        this.prenom = u.getPrenom();
        this.email = u.getEmail();
        this.role = u.getRole().toString();
    }
}
