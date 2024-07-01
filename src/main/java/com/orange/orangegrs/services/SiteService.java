package com.orange.orangegrs.services;

import com.orange.orangegrs.entities.Site;
import com.orange.orangegrs.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface SiteService {

    List<Site> findAll();
    List<Site> findBySiteCode(String code);


    Site findSiteBySiteId(int siteId);


    Page<Site> findAllSites(Pageable pageable);


    Page<Site> getSitesByRefOrCode(Pageable pageable,String code);


    Page<Site> getAllSiteNonPasEncoreUneVisite(Pageable pageable, Date dateMin);


    Page<Site> getAllSiteVisiterAvecDateFiltre(Pageable pageable, Date dateMin);
}
