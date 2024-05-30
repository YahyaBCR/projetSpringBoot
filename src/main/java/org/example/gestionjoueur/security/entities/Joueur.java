package org.example.gestionjoueur.security.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Joueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 4 , max = 50)
    private String nomcomplet;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date dateNaissance;
    private boolean blessure;
    @DecimalMin("100")
    private int minutesJouee;


    public Joueur(String nomcomplet, Date dateNaissance, Boolean blessure, int minutesJouee){
        this.nomcomplet = nomcomplet;
        this.dateNaissance = dateNaissance;
        this.blessure = blessure;
        this.minutesJouee = minutesJouee;
    }




    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nomcomplet;
    }

    public void setNom(String nomcomplet) {
        this.nomcomplet = nomcomplet;
    }



}
