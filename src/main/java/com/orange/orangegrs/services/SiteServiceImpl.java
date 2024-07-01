package com.orange.orangegrs.services;

import com.orange.orangegrs.entities.Site;
import com.orange.orangegrs.repositories.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SiteServiceImpl implements SiteService{

    @Autowired
    SiteRepository siteRepository;


    @Override
    public List<Site> findAll() {
        return this.siteRepository.findAll();
    }

    @Override
    public List<Site> findBySiteCode(String code) {
        if(code.matches("\\d+")){
            int ref = Integer.parseInt(code);
            return siteRepository.findSitesByRef(ref);
        }else{
            return siteRepository.findSitesByCode(code);
        }
    }

    @Override
    public Site findSiteBySiteId(int siteId) {
        return this.siteRepository.findSiteBySiteId(siteId);
    }

    @Override
    public Page<Site> findAllSites(Pageable pageable) {
        return this.siteRepository.findAll(pageable);
    }

    @Override
    public Page<Site> getSitesByRefOrCode(Pageable pageable, String code) {
        if(code.matches("\\d+")){
            int ref = Integer.parseInt(code);
            return siteRepository.findSitesByRefPagination(pageable,ref);
        }else{
            return siteRepository.findSitesByCodePagination(pageable,code);
        }
    }

    @Override
    public Page<Site> getAllSiteNonPasEncoreUneVisite(Pageable pageable, Date dateMin) {
        return this.siteRepository.findSiteNonVisiteWithDateMin(pageable, dateMin);
    }

    @Override
    public Page<Site> getAllSiteVisiterAvecDateFiltre(Pageable pageable, Date dateMin) {
        return this.siteRepository.findSiteVisiterWithDateMin(pageable, dateMin);
    }
}
