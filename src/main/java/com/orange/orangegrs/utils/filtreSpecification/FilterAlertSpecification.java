package com.orange.orangegrs.utils.filtreSpecification;

import com.orange.orangegrs.entities.AlertVisit;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class FilterAlertSpecification {
    public static Specification<AlertVisit> hasType(int type) {
        System.out.println("on a ajouter le type dans le filter = "+type);
        return (root, query, builder) -> builder.equal(root.get("alertTypes").get("alertTypeId"), type);
    }

    public static Specification<AlertVisit> hasDateAlert(Date dateAlert) {
        System.out.println("on a ajouter le date dans le filter = "+dateAlert);
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("dateAlert"), dateAlert);
    }

    public static Specification<AlertVisit> hasCodeSite(String codeSite) {
        System.out.println("on a ajouter le code site dans le filter = "+codeSite);
        return (root, query, builder) -> builder.like(root.get("site").get("siteCode"), codeSite+"%");
    }

    public static Specification<AlertVisit> hasPourcentage(int pourcentage) {
        System.out.println("on a ajouter le pourcentage dans le filter = "+pourcentage);
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("difference"), pourcentage);
    }
}
