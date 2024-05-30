package org.example.gestionjoueur.security.service;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.gestionjoueur.security.entities.AppRole;
import org.example.gestionjoueur.security.entities.AppUser;
import org.example.gestionjoueur.security.repo.AppRoleRepository;
import org.example.gestionjoueur.security.repo.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{

    private AppRoleRepository appRoleRepository;
    private AppUserRepository appUserRepository;

    private PasswordEncoder passwordEncoder;





    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPassword) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser!=null) throw new RuntimeException("Utilisateur deja existe !!!");
        if(!password.equals(confirmPassword)) throw new RuntimeException("Mot de passe incorrecte");
        appUser = AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email).build();


        AppUser savedAppUser  = appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole addNewRole(String role) {
        AppRole appRole = appRoleRepository.findById(role).orElse(null);

        if (appRole != null) throw new RuntimeException("Role existe deja !!!");
        appRole = AppRole.builder().role(role).build();
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findById(role).get();
        appUser.getRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findById(role).get();
        appUser.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
