package com.orange.orangegrs.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AlertJobs {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AlertJobId", nullable = false)
    private short alertJobId;


    @Column(name = "NameJob", nullable = false)
    private String nameJob;


    @Column(name = "Description")
    private String description ;

    @Column(name = "IsActive")
    private boolean isActive;
}
