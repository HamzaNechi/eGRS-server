package com.orange.orangegrs.utils.filtreSpecification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterAlertRequest {
    private int type;
    private String codeSite;
    private Date dateAlert;
    private int pourcentage;
}
