package com.orange.orangegrs.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;



@Data
@Entity
@Table(name = "Visites")
public class Visite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VisiteId")
    private int visiteId;


    @Column(name = "IndexCompteur", nullable = false)
    private long indexCompteur;

    @Column(name = "PhotoCompteur", nullable = false)
    private String photoCompteur;


    @Column(name = "DateInsertion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInsertion;

    @Column(name = "Commentaire")
    @Lob
    private String commentaire;



    @ManyToOne
    @JoinColumn(name = "Login")
    private User login;



    @ManyToOne
    @JoinColumn(name = "SiteId")
    private Site site;

    @Column(name = "AmperageOTN", nullable = false, columnDefinition =  "INT DEFAULT 0")
    private int otn;

    @Column(name = "AmperageOO", nullable = false, columnDefinition =  "INT DEFAULT 0")
    private int oo;


    @Column(name = "IndexOO", nullable = false, columnDefinition = "FLOAT DEFAULT 0.0")
    private double indexOO;


    @Column(name = "AmperageTT", nullable = false, columnDefinition =  "INT DEFAULT 0")
    private int tt;

    @Column(name = "IndexTT", nullable = false, columnDefinition = "FLOAT DEFAULT 0.0")
    private double indexTT;



    @PrePersist
    protected void onCreate() {
        dateInsertion = new Date(); // Définit la date d'insertion au moment de la création
    }
}
