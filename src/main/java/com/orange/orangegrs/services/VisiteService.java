package com.orange.orangegrs.services;

import com.orange.orangegrs.entities.Visite;
import com.orange.orangegrs.utils.filtreSpecification.FilterVisitRequest;

import java.util.Date;
import java.util.List;

public interface VisiteService {
    Visite addNewVisite(Visite visite);
    List<Visite> getAllVisite();

    List<Visite> getAllVisiteByUserLogin(String login);

    List<Visite> getAllVisiteByUserLoginAndSiteCode(String login, String siteCode);


    List<Visite> findVisiteByCodeSite(String siteCode);


    Visite findOne(int visiteId);

    int deleteVisiteByVisiteId(int visiteId);

    Visite updateVisite(Visite visite);


    List<Visite> getLastTwhoInserted(int siteId);



    List<Visite> findVisitAddedToDay(Date now);


    List<Visite> filtreVisites(FilterVisitRequest filterVisitRequest);
}
