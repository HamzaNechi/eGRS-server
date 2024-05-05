package com.orange.orangegrs.entities;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.Date;

@Entity
@Data
@Table(name = "Invoices")
public class Invoice {


    @Id
    @Column(name = "InvoiceId")
    private int invoiceId;

    @ManyToOne
    @JoinColumn(name = "Login")
    private User login;



    @ManyToOne
    @JoinColumn(name = "ElecTypeId")
    private ElectrificationTypes elecTypeId;


    @Column(name = "EmissionDate")
    @Temporal(TemporalType.TIMESTAMP)
    @Nullable
    private Date emissionDate;



    @Column(name = "Month")
    private short month;



    @Column(name = "Year")
    private short year;


    @Nullable
    @Column(name = "MonthEnd", columnDefinition = "TINYINT(2)")
    private Byte monthEnd;


    @Column(name = "YearEnd")
    @Nullable
    private Short yearEnd;


    @Column(name = "ImportDate")
    @Temporal(TemporalType.TIMESTAMP)
    @Nullable
    private Date importDate;


    @Column(name = "Zone", length = 50)
    @Nullable
    private String zone;
}
