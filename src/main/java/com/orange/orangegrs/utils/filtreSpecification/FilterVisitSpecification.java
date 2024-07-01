package com.orange.orangegrs.utils.filtreSpecification;


import com.orange.orangegrs.entities.Visite;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class FilterVisitSpecification {

    public static Specification<Visite> hasIndex(int index) {
        System.out.println("on a ajouter index dans le filtre "+ index);
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("indexCompteur"), index);
    }


    public static Specification<Visite> hasSiteCode(String siteCode) {
        System.out.println("on a ajouter code site dans le filtre "+ siteCode);
        return (root, query, builder) -> builder.like(root.get("site").get("siteCode"), siteCode+'%');
    }


    public static Specification<Visite> hasDateInsertion(Date dateInsertion) {
        System.out.println("on a ajouter date dans le filtre "+ dateInsertion);
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("dateInsertion"), dateInsertion);
    }


    public static Specification<Visite> hasResponsable(String responsable) {
        System.out.println("on a ajouter responsable dans le filtre "+ responsable);
        return (root, query, builder) -> builder.like(root.get("login").get("login"), '%'+responsable+'%');
    }
}
