package org.demo.kaiaskin.dao.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private String nom;

    private String prenom;

    private String email;

    private String motDePasse;

}
