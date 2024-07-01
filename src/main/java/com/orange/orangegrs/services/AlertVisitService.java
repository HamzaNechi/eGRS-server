package com.orange.orangegrs.services;

import com.orange.orangegrs.entities.AlertVisit;
import com.orange.orangegrs.utils.filtreSpecification.FilterAlertRequest;

import java.util.List;
import java.util.Map;

public interface AlertVisitService {

    void alertHandler(int siteId);

    List<AlertVisit> getAllAlerts();


    void alertTypeFactureHandler();


    List<AlertVisit> filterAlerts(FilterAlertRequest filterAlertRequest);

    Map<String, Integer> distributionTypeOfAlert();
}
