package com.orange.orangegrs.entities;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "Sites")
public class Site {

    @Id
    @Nonnull
    @Column( name = "SiteId")
    private int siteId;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "StatusId", referencedColumnName = "StatusId")
    private SiteStatus statusId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ElecTypeId", referencedColumnName = "ElecTypeId")
    private ElectrificationTypes elecTypeId ;

    @Nullable
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DirectionId", referencedColumnName = "DirectionId")
    private Directions directionId;

    @Nullable
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "NetworkTypeId", referencedColumnName = "NetworkTypeId")
    private NetworkTypes NetworkTypeId;

    @Nullable
    @Column( name = "SiteCode")
    private String siteCode;

    @Nullable
    @Column( name = "SiteName")
    private String siteName;

    @Nonnull
    @Column( name = "ElecMeterRef")
    private int elecMeterRef;

    @Nullable
    @Temporal(TemporalType.DATE)
    @Column( name = "ElecDate")
    private Date elecDate;


    @Nullable
    @Column( name = "Configuration")
    private String configuration;

    @Nonnull
    @Column( name = "IsSharing")
    private boolean isSharing;

    @Nullable
    @Column( name = "EstimatedConsumption")
    private Long estimatedConsumption;


    @Column( name = "MaxConsumption")
    @Nullable
    private Long maxConsumption;
}
