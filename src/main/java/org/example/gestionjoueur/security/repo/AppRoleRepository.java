package org.example.gestionjoueur.security.repo;

import org.example.gestionjoueur.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,String> {


}
