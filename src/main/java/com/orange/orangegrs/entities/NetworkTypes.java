package com.orange.orangegrs.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "NetworkTypes")
public class NetworkTypes {

    @Id
    @Column(name = "NetworkTypeId")
    private byte networkTypeId;

    @Column(name = "Description", length = 200)
    private String description;
}
