package org.demo.kaiaskin.dao.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.demo.kaiaskin.models.mysql.enums.Roles;

@AllArgsConstructor
@Getter
@Setter
public class AuthResponse {
    private String token;
    private String email;
    private Roles role;

    // Getters & Setters
}

