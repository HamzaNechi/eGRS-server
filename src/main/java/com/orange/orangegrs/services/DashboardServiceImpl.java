package com.orange.orangegrs.services;


import com.orange.orangegrs.repositories.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DashboardServiceImpl implements DashboardService{

    @Autowired
    private AlertVisitService alertVisitService;



    @Autowired
    private InvoiceItemRepository invoiceItemRepository;


    @Override
    public Map<String, Integer> distributionOfAlertWithType() {
        return this.alertVisitService.distributionTypeOfAlert();
    }


    @Override
    public List<Object[]> getConsumptionSummaryForLastSixMonths() {
        // Calculer la date pour les six derniers mois
        Calendar calendar = Calendar.getInstance();
        //calendar.add(Calendar.MONTH, -6);
        //env de test
        calendar.set(2023, Calendar.AUGUST, 1);
        //end env test
        Date sixMonthsAgo = calendar.getTime();


        return this.invoiceItemRepository.findConsumptionSummaryForLastSixMonths(sixMonthsAgo);
    }

    @Override
    public List<Object[]> getConsommationSelonTypeSite() {
        // Calculer la date pour les six derniers mois
        Calendar calendar = Calendar.getInstance();
        //calendar.add(Calendar.MONTH, -6);
        //env de test
        calendar.set(2023, Calendar.AUGUST, 1);
        //end env test
        Date sixMonthsAgo = calendar.getTime();
        return this.invoiceItemRepository.findConsumptionWithTypeSiteForLastSixMonths(sixMonthsAgo);
    }

    @Override
    public List<Object[]> consommationDepuisPremierJanvier() {
        /*Calendar calendar= Calendar.getInstance();
        int startYear=calendar.getTime().getYear();*/
        return this.invoiceItemRepository.findConsumptionDepuisPremierJanvier(2023);
    }
}
