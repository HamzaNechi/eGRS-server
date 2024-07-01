package com.orange.orangegrs.repositories;

import com.orange.orangegrs.entities.AlertTypes;
import com.orange.orangegrs.entities.AlertVisit;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AlertVisitRepository extends JpaRepository<AlertVisit, Integer> , JpaSpecificationExecutor<AlertVisit> {
    List<AlertVisit> findAll();

    List<AlertVisit> findAll(Specification spec);

    AlertVisit findFirstByAlertTypes_AlertTypeIdAndSite_SiteId(int alertType, int siteId);


    int countAlertVisitByAlertTypes_AlertTypeId(int alertType);
}
