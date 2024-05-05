package com.orange.orangegrs.services;

import com.orange.orangegrs.entities.Visite;
import com.orange.orangegrs.repositories.VisiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return this.visiteRepository.findAllBySite_SiteCodeAndLogin_LoginOrderByDateInsertionDesc(siteCode,login);
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
}
