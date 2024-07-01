package com.orange.orangegrs.repositories;

import com.orange.orangegrs.entities.Visite;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface VisiteRepository extends JpaRepository<Visite, Integer>, JpaSpecificationExecutor<Visite> {

    List<Visite> findAll(Specification spec);

    List<Visite> findAllByOrderByDateInsertionDesc();

    List<Visite> findAllByLogin_LoginOrderByDateInsertionDesc(String login);
    List<Visite> findAllBySite_SiteCodeOrderByDateInsertionDesc(String siteCode);


    List<Visite> findAllBySite_SiteCodeAndLogin_LoginOrderByDateInsertionDesc(String siteCode, String login);


    @Query("select v from Visite v, Site s where v.site.siteId = s.siteId and s.siteCode = :siteCode order by v.dateInsertion desc limit 4")
    List<Visite> getAllVisitAccessTechnicien(String siteCode);


    int deleteByVisiteId(int visiteId);


    Visite findVisiteByVisiteId(int visiteId);



    List<Visite> findTop2BySite_SiteIdOrderByDateInsertionDesc(int siteId);


    @Query("SELECT v FROM Visite v WHERE CAST(v.dateInsertion AS date) = CAST(:date AS date)")
    List<Visite> findVisiteByDateInsertionEquals(Date date);



}
