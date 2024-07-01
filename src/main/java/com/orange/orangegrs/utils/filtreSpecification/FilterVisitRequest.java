package com.orange.orangegrs.utils.filtreSpecification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterVisitRequest {
    private int index;
    private String siteCode;
    private Date dateInsertion;
    private String responsable;
}
