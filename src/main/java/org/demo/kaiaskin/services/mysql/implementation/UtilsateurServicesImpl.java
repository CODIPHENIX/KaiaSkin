package org.demo.kaiaskin.services.mysql.implementation;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.demo.kaiaskin.dao.request.RegisterRequest;
import org.demo.kaiaskin.dao.request.UpdateUserRequest;
import org.demo.kaiaskin.dto.UserDto;
import org.demo.kaiaskin.models.mysql.Utilisateur;
import org.demo.kaiaskin.models.mysql.enums.Roles;
import org.demo.kaiaskin.repositories.mysql.UtilisateurRepository;
import org.demo.kaiaskin.services.mysql.UtilsateurServices;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilsateurServicesImpl implements UtilsateurServices {

    private final UtilisateurRepository utilisateurRepository;

    private final PasswordEncoder passwordEncoder;
    public List<Utilisateur> getAllUser(){
        return utilisateurRepository.findAll();
    }
    public Optional<Utilisateur> getUserbyId(Long id){
        return utilisateurRepository.findById(id);
    }
    public Optional<Utilisateur> getUserbyEmail(String email){
        return utilisateurRepository.findByEmail(email);
    }
    public Utilisateur register( RegisterRequest request) {
        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setNom(request.getNom());
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        utilisateur.setRole(Roles.valueOf(request.getRole().toUpperCase()));

        return utilisateurRepository.save(utilisateur);
    }
    public Utilisateur update(UpdateUserRequest utilisateur, Utilisateur utilisateurConnecter){

        if(isValid(utilisateur.getNom())){
            utilisateurConnecter.setNom(utilisateur.getNom());
        }
        if(isValid(utilisateur.getPrenom())){
            utilisateurConnecter.setPrenom(utilisateur.getPrenom());
        }
        if(isValid(utilisateur.getEmail())){
            utilisateurConnecter.setEmail(utilisateur.getEmail());
        }
        if(isValid(utilisateur.getMotDePasse())){
            utilisateurConnecter.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        }
        utilisateurConnecter.setDateModification(LocalDateTime.now());
        return utilisateurRepository.save(utilisateurConnecter);
    }
    public boolean softDeleteUser(Long id){
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Cette utilisateur n'exist pas."));
        if (utilisateur.isEstSupprimer()){
            return false;
        }
        utilisateur.setEstSupprimer(true);
        utilisateur.setDateSuppression(LocalDateTime.now());
        utilisateurRepository.save(utilisateur);

        return true;
    }

    public boolean softDeleteMe(Utilisateur utilisateur){

        if (utilisateur.isEstSupprimer()){
            return false;
        }
        utilisateur.setEstSupprimer(true);
        utilisateur.setDateSuppression(LocalDateTime.now());
        utilisateurRepository.save(utilisateur);
        return true;
    }
    public List<Utilisateur> findUserByNameIgnoreCase(String name){
        return utilisateurRepository.findAllByNomIgnoreCase(name);
    }
    public List<Utilisateur> findUserByNameContainingIgnoreCase(String name){
        return utilisateurRepository.findAllByNomContainingIgnoreCase(name);
    }
    public List<Utilisateur> findUserWithDiagnostic(){
        return utilisateurRepository.findUserWithAtLeastOneDiagnostic();
    }
    public boolean deleteUser(Long id){
        Optional<Utilisateur> exist = utilisateurRepository.findById(id);
        if(exist.isEmpty()){
            return false;
        }
            utilisateurRepository.deleteById(id);
            return true;


    }
    public void deleteAllUser(){
            utilisateurRepository.deleteAll();
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void hardScheduleDelete(){
        LocalDateTime dateline= LocalDateTime.now().minusDays(30);
//        LocalDateTime dateline= LocalDateTime.now().minusMinutes(5);
        List<Utilisateur> aSupprimer = utilisateurRepository.findAllByEstSupprimerTrueAndDateSuppressionBefore(dateline);

        utilisateurRepository.deleteAll(aSupprimer);
        System.out.println("bien supprimer!!!!!!!  "+   aSupprimer.stream().map(UserDto::new).toList());
    }

    public boolean emailExit(String email){return utilisateurRepository.existsByEmail(email);}
    public boolean isValid(String value) {
        return value != null && !value.isBlank();
    }

    public List<Utilisateur> getAllUserByisDeletefalse(){
        return utilisateurRepository.findAllByEstSupprimerIsFalse();
    }
    public Utilisateur reactivateAccount(Utilisateur utilisateur, RegisterRequest request){
        utilisateur.setNom(request.getNom());
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        utilisateur.setEstSupprimer(false);
        utilisateur.setRole(Roles.valueOf(request.getRole().toUpperCase()));

        return utilisateurRepository.save(utilisateur);
    }
}
