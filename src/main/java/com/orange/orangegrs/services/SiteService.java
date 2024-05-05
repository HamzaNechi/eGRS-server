package com.orange.orangegrs.services;

import com.orange.orangegrs.entities.Site;

import java.util.List;

public interface SiteService {

    List<Site> findAll();
    List<Site> findBySiteCode(String code);


    Site findSiteBySiteId(int siteId);
}
