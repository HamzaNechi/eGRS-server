package com.orange.orangegrs.services;

import java.util.List;
import java.util.Map;

public interface DashboardService {
    Map<String, Integer> distributionOfAlertWithType();

    List<Object[]> getConsumptionSummaryForLastSixMonths();

    List<Object[]> getConsommationSelonTypeSite();


    List<Object[]> consommationDepuisPremierJanvier();
}
