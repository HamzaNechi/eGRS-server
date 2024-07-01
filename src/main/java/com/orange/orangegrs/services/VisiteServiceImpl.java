package com.orange.orangegrs.services;

import com.orange.orangegrs.entities.Visite;
import com.orange.orangegrs.repositories.VisiteRepository;
import com.orange.orangegrs.utils.filtreSpecification.FilterVisitRequest;
import com.orange.orangegrs.utils.filtreSpecification.FilterVisitSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class VisiteServiceImpl implements VisiteService{

    @Autowired
    private VisiteRepository visiteRepository;

    @Override
    public Visite addNewVisite(Visite visite) {
        return this.visiteRepository.save(visite);
    }

    @Override
    public List<Visite> getAllVisite() {
        return this.visiteRepository.findAllByOrderByDateInsertionDesc();
    }

    @Override
    public List<Visite> getAllVisiteByUserLogin(String login) {
        return this.visiteRepository.findAllByLogin_LoginOrderByDateInsertionDesc(login);
    }

    @Override
    public List<Visite> getAllVisiteByUserLoginAndSiteCode(String login, String siteCode) {
        return this.visiteRepository.getAllVisitAccessTechnicien(siteCode);
    }

    @Override
    public List<Visite> findVisiteByCodeSite(String siteCode) {
        return this.visiteRepository.findAllBySite_SiteCodeOrderByDateInsertionDesc(siteCode);
    }

    @Override
    public Visite findOne(int visiteId) {
        return this.visiteRepository.findVisiteByVisiteId(visiteId);
    }

    @Override
    @Transactional
    public int deleteVisiteByVisiteId(int visiteId) {
        return this.visiteRepository.deleteByVisiteId(visiteId);
    }

    @Override
    public Visite updateVisite(Visite visite) {
        return this.visiteRepository.save(visite);
    }

    @Override
    public List<Visite> getLastTwhoInserted(int siteId) {
        return this.visiteRepository.findTop2BySite_SiteIdOrderByDateInsertionDesc(siteId);
    }

    @Override
    public List<Visite> findVisitAddedToDay(Date now) {
        return this.visiteRepository.findVisiteByDateInsertionEquals(now);
    }

    @Override
    public List<Visite> filtreVisites(FilterVisitRequest filterVisitRequest) {
        Specification<Visite> spec = Specification.where(null);

        if(filterVisitRequest.getResponsable() != null){
            spec=Specification.where(spec).and(FilterVisitSpecification.hasResponsable(filterVisitRequest.getResponsable()));
        }

        if(filterVisitRequest.getIndex() > 0){
            spec=Specification.where(spec).and(FilterVisitSpecification.hasIndex(filterVisitRequest.getIndex()));
        }


        if(filterVisitRequest.getSiteCode() != null){
            spec=Specification.where(spec).and(FilterVisitSpecification.hasSiteCode(filterVisitRequest.getSiteCode()));
        }


        if(filterVisitRequest.getDateInsertion() != null){
            spec=Specification.where(spec).and(FilterVisitSpecification.hasDateInsertion(filterVisitRequest.getDateInsertion()));
        }

        return this.visiteRepository.findAll(spec);
    }
}
