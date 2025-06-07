package org.demo.kaiaskin.services.mysql;


import org.demo.kaiaskin.dao.request.RegisterRequest;
import org.demo.kaiaskin.dao.request.UpdateUserRequest;
import org.demo.kaiaskin.models.mysql.Utilisateur;

import java.util.List;
import java.util.Optional;


public interface UtilsateurServices {

   List<Utilisateur> getAllUser();
   Optional<Utilisateur> getUserbyId(Long id);
   Optional<Utilisateur> getUserbyEmail(String email);
    Utilisateur register(RegisterRequest request);
    Utilisateur reactivateAccount(Utilisateur utilisateur, RegisterRequest request);
    Utilisateur update(UpdateUserRequest utilisateur, Utilisateur utilisateurConnecter);
    boolean softDeleteUser(Long id);
    List<Utilisateur> findUserByNameIgnoreCase(String name);
    List<Utilisateur> findUserByNameContainingIgnoreCase(String name);
    List<Utilisateur> findUserWithDiagnostic();

    List<Utilisateur> getAllUserByisDeletefalse();
    boolean softDeleteMe(Utilisateur utilisateur);
    boolean deleteUser(Long id);
    void deleteAllUser();
    boolean emailExit(String email);

}
