package com.orange.orangegrs.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VisiteRequest {
    private int visiteId;
    private long indexCompteur;
    private String photoCompteur;
    private String commentaire;
    private int siteId;
    private int otn;
    private int oo;
    private int tt;
    private double indexOO;
    private double indexTT;
}
