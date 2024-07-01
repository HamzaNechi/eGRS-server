package com.orange.orangegrs.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table( name = "AlertVisit")
public class AlertVisit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int alertVisitId;


    @Column(name = "DateAlert", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAlert;


    @ManyToOne
    @JoinColumn(name = "SiteId", nullable = false)
    private Site site;


    @ManyToOne
    @JoinColumn(name = "AlertTypeId",nullable = false)
    private AlertTypes alertTypes;


    @Column(name = "RequiredConsommation")
    private double requiredConsommation ;

    @Column(name = "StegEstConsommation")
    private double stegEstConsommation ;


    @Column(name = "Difference")
    private double difference ;



    @PrePersist
    protected void onCreate() {
        dateAlert = new Date();
    }

}
