package com.orange.orangegrs.repositories;

import com.orange.orangegrs.entities.Visite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisiteRepository extends JpaRepository<Visite, Integer> {

    List<Visite> findAllByOrderByDateInsertionDesc();

    List<Visite> findAllByLogin_LoginOrderByDateInsertionDesc(String login);
    List<Visite> findAllBySite_SiteCodeOrderByDateInsertionDesc(String siteCode);


    List<Visite> findAllBySite_SiteCodeAndLogin_LoginOrderByDateInsertionDesc(String siteCode, String login);


    int deleteByVisiteId(int visiteId);


    Visite findVisiteByVisiteId(int visiteId);

}
