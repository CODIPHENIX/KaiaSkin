package org.demo.kaiaskin.controllers.mysql.auth;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.demo.kaiaskin.dao.request.AuthRequest;
import org.demo.kaiaskin.dao.request.RegisterRequest;
import org.demo.kaiaskin.dao.response.AuthResponse;
import org.demo.kaiaskin.dao.response.RegisterResponse;
import org.demo.kaiaskin.dto.UserDto;
import org.demo.kaiaskin.models.mysql.Utilisateur;
import org.demo.kaiaskin.models.mysql.enums.Roles;
import org.demo.kaiaskin.services.mysql.JwtService;
import org.demo.kaiaskin.services.mysql.UtilsateurServices;
import org.demo.kaiaskin.tools.Tools;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UtilsateurServices userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        if ( Tools.isInvalide(request.getEmail())
                || Tools.isInvalide(request.getMotDePasse())
        ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Veuillez remplir tous les champs.");
        }
        Utilisateur user = userService.getUserbyEmail(request.getEmail())
                .orElseThrow(()->new EntityNotFoundException("Utilisateur non trouvé."));

        if(user.isEstSupprimer()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants invalides, ce compte n'exist plus");
        }

        if (!passwordEncoder.matches(request.getMotDePasse(), user.getMotDePasse())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants invalides");
        }

        String token = jwtService.generateToken(user,user.getRole());

        AuthResponse response = new AuthResponse(
                token,
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity.ok(response);
    }
    @PostMapping("/sign_up")
    public RegisterResponse register(@RequestBody RegisterRequest request) {

        if ( Tools.isInvalide(request.getEmail())
                || Tools.isInvalide(request.getMotDePasse())
                || Tools.isInvalide( request.getNom() )
                || Tools.isInvalide(request.getPrenom())
                || request.getRole() == null
                || !Tools.isValidEnum( Roles.class,request.getRole())

        ) {
            return new RegisterResponse("Veuillez remplir tous les champs.",null);
        }


        Optional<Utilisateur> UserExist = userService.getUserbyEmail(request.getEmail());

        if(UserExist.isPresent()){
            Utilisateur user = UserExist.get();
            if(user.isEstSupprimer()){
              Utilisateur  utilisateur = userService.reactivateAccount(user, request);
              return responseAvecToken(utilisateur,"Reinscription réussie");

            }else {
                return new RegisterResponse("Email déjà utilisé", null);
            }
        }

        Utilisateur nouvelUtilisateur = userService.register(request);
        return responseAvecToken(nouvelUtilisateur,"Inscription réussie");
    }

    private RegisterResponse responseAvecToken(Utilisateur utilisateur, String message) {
        String token = jwtService.generateToken(utilisateur, utilisateur.getRole());
        return new RegisterResponse(message, new UserDto(utilisateur, token));
    }


}

