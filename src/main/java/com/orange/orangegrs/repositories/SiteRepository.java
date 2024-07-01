package com.orange.orangegrs.repositories;

import com.orange.orangegrs.entities.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SiteRepository extends JpaRepository<Site, Long> {
    @Query("SELECT s FROM Site s WHERE s.siteCode LIKE CONCAT(:code, '%')")
    List<Site> findSitesByCode(String code);



    @Query("SELECT s FROM Site s WHERE s.elecMeterRef = :ref")
    List<Site> findSitesByRef(int ref);


    @Query("SELECT s FROM Site s WHERE s.siteId = :siteId ")
    Site findSiteBySiteId(int siteId);



    Page<Site> findAll(Pageable pageable);


    @Query("SELECT s FROM Site s WHERE s.siteCode LIKE CONCAT(:code, '%')")
    Page<Site> findSitesByCodePagination(Pageable pageable,String code);



    @Query("SELECT s FROM Site s WHERE s.elecMeterRef = :ref")
    Page<Site> findSitesByRefPagination(Pageable pageable,int ref);


    @Query("SELECT s FROM Site s LEFT JOIN Visite v ON s.siteId = v.site.siteId AND v.dateInsertion >= :dateMin WHERE v.site.siteId IS NULL")
    Page<Site> findSiteNonVisiteWithDateMin(Pageable pageable, Date dateMin);


    @Query("SELECT s FROM Site s INNER JOIN Visite v ON s.siteId = v.site.siteId AND v.dateInsertion >= :dateMin")
    Page<Site> findSiteVisiterWithDateMin(Pageable pageable, Date dateMin);



}
