package com.orange.orangegrs.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "SiteStatus")
public class SiteStatus {

    @Id
    @Column(name = "StatusId")
    private byte statusId;


    @Column(name = "Description", length = 200)
    private String description;
}
