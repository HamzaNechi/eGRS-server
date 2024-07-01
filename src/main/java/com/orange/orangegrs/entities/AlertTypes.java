package com.orange.orangegrs.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AlertTypes")
public class AlertTypes {

    @Id
    @Column(name = "AlertTypeId", nullable = false, columnDefinition = "TINYINT")
    private short alertTypeId;

    @Column(name = "Description", length = 200,nullable = false)
    private String description;
}
