package com.orange.orangegrs.utils;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class DateUtils {

    public static boolean areDatesMonthlyAndSequential(List<Date> dates) {
        if (dates.isEmpty()) {
            return true; // Une liste vide est considérée comme valide
        }

        // Trier les dates par année et mois
        dates.sort(Comparator.comparing(Date::getYear).thenComparing(Date::getMonth));

        // Vérifier que chaque mois/année est unique et séquentiel
        LocalDate previousDate = dates.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        for (int i = 1; i < dates.size(); i++) {
            LocalDate currentDate = dates.get(i).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // Vérifier si le mois et l'année sont consécutifs
            LocalDate expectedDate = previousDate.plusMonths(1);
            if (!currentDate.equals(expectedDate)) {
                return false; // Les dates ne sont pas séquentielles
            }

            previousDate = currentDate;
        }

        return true; // Toutes les dates sont uniques et séquentielles
    }


    public static int differenceEnMois(Date startDate, Date endDate) {
        LocalDate date1 = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date2 = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return (int) ChronoUnit.MONTHS.between(date1, date2);
    }

}
