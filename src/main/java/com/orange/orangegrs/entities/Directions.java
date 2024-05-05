package com.orange.orangegrs.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Directions")
public class Directions {


    @Id
    @Column(name = "DirectionId")
    private short directionId;


    @Column(name = "Description", length = 200)
    private String description;
}
