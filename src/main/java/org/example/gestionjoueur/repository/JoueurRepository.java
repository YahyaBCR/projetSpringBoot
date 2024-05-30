package org.example.gestionjoueur.repository;

import org.example.gestionjoueur.security.entities.Joueur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JoueurRepository extends JpaRepository<Joueur,Long> {
    Page<Joueur> findByNomContains(String keyword , Pageable pageable);
    @Query("select j from Joueur j where j.nomcomplet like :x")
    Page<Joueur> chercher(@Param("x") String keyword, Pageable pageable);
}




