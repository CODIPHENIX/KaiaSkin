package org.demo.kaiaskin.controllers.mysql;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.demo.kaiaskin.dao.request.UpdateUserRequest;
import org.demo.kaiaskin.dto.UserDto;
import org.demo.kaiaskin.models.mysql.Utilisateur;

import org.demo.kaiaskin.services.mysql.UtilsateurServices;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/utilisateurs")
@RequiredArgsConstructor
public class UtilsateurController {

    public final UtilsateurServices utilsateurServices;
    @GetMapping("/test")
    public String test(){
        return "Api ok";
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAlluser(){
        return  ResponseEntity.ok(utilsateurServices.getAllUserByisDeletefalse()
                .stream()
                .map(UserDto::new)
                .toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> GetUser(@PathVariable("id") Long id){
        Optional<Utilisateur> userOpt = utilsateurServices.getUserbyId(id);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("Cet utilisateur n'existe pas.");
        }
        Utilisateur user = userOpt.get();
        return ResponseEntity.ok(new UserDto(user));
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateUtilisateur(
            @AuthenticationPrincipal Utilisateur utilisateurConnecte,
            @RequestBody UpdateUserRequest request
    ) {
        utilsateurServices.update(request,utilisateurConnecte);
        return ResponseEntity.ok(new UserDto(utilisateurConnecte));
    }
    @GetMapping("/profil")
    public ResponseEntity<?> userProfil(
            @AuthenticationPrincipal Utilisateur utilisateurConnecter
    ){
//        System.out.println("Utilisateur connecté : " + utilisateurConnecter.getEmail());

        return ResponseEntity.ok(new UserDto(utilisateurConnecter));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try {
            boolean deleted = utilsateurServices.softDeleteUser(id);
            if(!deleted){
              return   ResponseEntity.status(409).body("Cette utilisater n'exit plus.");
            }
            return ResponseEntity.ok("Utilisateur supprimé avec succès.");
        }catch (EntityNotFoundException ex){
           return ResponseEntity.status(404).body(ex.getMessage());
        }

    }
    @DeleteMapping("/deleteMyAccount")
    public ResponseEntity<?> deleteMe(
            @AuthenticationPrincipal Utilisateur utilisateurConnecter){
        boolean deleted = utilsateurServices.softDeleteMe(utilisateurConnecter);
        if(!deleted){
            return   ResponseEntity.status(409).body("Compte Introuvable.");
        }
        return ResponseEntity.ok("Supprimé avec succès.");
    }

    @DeleteMapping("/hardDelete/{id}")
    public ResponseEntity<?> hardDeleteUser(@PathVariable Long id){
        boolean deleteFinal = utilsateurServices.deleteUser(id);
        if(!deleteFinal){
            return ResponseEntity.status(404).body("Utilisateur introuvable ou déjà supprimé.");
        }
        return ResponseEntity.ok("Utilisateur supprimé definitivement avec succès.");
    }





}
