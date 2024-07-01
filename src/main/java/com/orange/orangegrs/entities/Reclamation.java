package com.orange.orangegrs.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Reclamation {
    @Id
    @Column(name = "ReclamationId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reclamationId;


    @Column(name = "Objet")
    private String objet;


    @Column(name = "Probleme")
    private String probleme;



    @Column(name = "Description")
    private String description;


    @Column(name = "DateInsertion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInsertion;


    @ManyToOne
    @JoinColumn(name = "Login")
    private User login;


    @PrePersist
    protected void onCreate() {
        dateInsertion = new Date(); // Définit la date d'insertion au moment de la création
    }

}
