package org.demo.kaiaskin.dao.request;

import lombok.Getter;
import lombok.Setter;
import org.demo.kaiaskin.models.mysql.enums.Roles;

@Getter
@Setter
public class RegisterRequest {

    private String nom;

    private String prenom;

    private String email;

    private String motDePasse;

    private String role;
}
