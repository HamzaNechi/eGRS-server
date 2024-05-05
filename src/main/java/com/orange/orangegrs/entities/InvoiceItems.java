package com.orange.orangegrs.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InvoiceItems {

    @Id
    @Column(name = "ItemId")
    private long itemId;


    @ManyToOne
    @JoinColumn(name = "InvoiceId")
    private Invoice invoice;


    @ManyToOne
    @JoinColumn(name = "SiteId", referencedColumnName = "SiteId")
    private Site siteId;

    @Nullable
    @Column(name = "ItemNo")
    private Integer itemNo;

    @Nullable
    @Column(name = "Description", length = 200)
    private String description;

    @Column(name = "ItemType")
    private short itemType;

    @Column(name = "ItemDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date itemDate;


    @Nullable
    @Column(name = "ConsumptionKwh")
    private Integer consumptionKwh;


    @Nullable
    @Column(name = "ConsumptionAmount", precision = 18, scale = 3)
    private BigDecimal consumptionAmount;


    @Nullable
    @Column(name = "Credit", precision = 18, scale = 3)
    private BigDecimal credit;


    @Nullable
    @Column(name = "Tva", precision = 18, scale = 3)
    private BigDecimal tva;


    @Column(name = "FinalSale", precision = 18, scale = 3)
    private BigDecimal finalSale;

    @Nullable
    @Column(name = "DistrictCode")
    private Short districtCode;



}
