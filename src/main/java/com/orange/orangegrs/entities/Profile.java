package com.orange.orangegrs.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "Profiles")
@Data
@EqualsAndHashCode
public class Profile {

    @Id
    @Column(name = "ProfileId")
    private short profileId;


    @Column(name = "Profile", length = 100, nullable = false)
    private String profile;



    @Column(name = "Description", length = 500)
    private String description;
}
