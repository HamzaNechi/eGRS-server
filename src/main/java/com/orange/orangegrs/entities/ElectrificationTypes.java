package com.orange.orangegrs.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "ElectrificationTypes")
public class ElectrificationTypes {

    @Id
    @Column(name = "ElecTypeId")
    private short elecTypeId;


    @Column(name = "Description", length = 100)
    private String description;
}
