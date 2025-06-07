package org.demo.kaiaskin.dao.response;
import lombok.*;
import org.demo.kaiaskin.dto.UserDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {

        private String message;
        private UserDto utilisateur;


    }
