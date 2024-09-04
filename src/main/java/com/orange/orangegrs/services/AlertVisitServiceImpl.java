package com.orange.orangegrs.services;


import com.orange.orangegrs.entities.*;
import com.orange.orangegrs.repositories.AlertVisitRepository;
import com.orange.orangegrs.utils.filtreSpecification.FilterAlertRequest;
import com.orange.orangegrs.utils.filtreSpecification.FilterAlertSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class AlertVisitServiceImpl implements AlertVisitService{

    @Autowired
    private VisiteService visiteService;

    @Autowired
    private InvoiceItemService invoiceItemService;


    @Autowired
    private SiteService siteService;


    @Autowired
    private AlertVisitRepository alertVisitRepository;



    @Override
    public void alertHandler(int siteId){
        List<Visite> visites = this.visiteService.getLastTwhoInserted(siteId);
        if (visites == null || visites.size() < 2){
            System.out.println("On ne peut pas comparer par une seule visite");
        }else{

            /************************* Calculer l'estimation de consommation par Visit terrain *****************/
            Visite visitePrecedente = visites.get(1);
            Visite visiteRecente = visites.get(0);
            long nombreJoursEntreVisites = ChronoUnit.DAYS.between(
                    visitePrecedente.getDateInsertion().toInstant(),
                    visiteRecente.getDateInsertion().toInstant());

            // Calculer la consommation moyenne par jour enregistrée par le technicien
            float consommationMesurerParTechnicienOrange = visiteRecente.getIndexCompteur() - visitePrecedente.getIndexCompteur();
            /************************* End Calculer l'estimation de consommation par Visit terrain *****************/





            /******************** Traitemnt de la liste des factures ***************************/
            List<InvoiceItems> facturesPeriodVisit = this.invoiceItemService.getInvoiceBetweenTwoVisit(
                    visites.get(0).getDateInsertion()  ,
                    visites.get(1).getDateInsertion() ,
                    siteId
            );

            Date dateVisitOne = visitePrecedente.getDateInsertion();
            Date dateVisitTwo = visiteRecente.getDateInsertion();
            facturesPeriodVisit = traitementFactures(facturesPeriodVisit, dateVisitOne, dateVisitTwo, siteId);
            /******************** End Traitemnt de la liste des factures ***************************/



            /******************** Extraire la consommation par jour de la liste des factures ***************************/
            double moyConsommationStegParJour = calculMoyConsommationStegParJours(facturesPeriodVisit);
            /******************** End Extraire la consommation par jour de la liste des factures ***************************/


            /******************** Calculer le pourcentage d'erreur entre la consommation enregistrer par STEG et la Consommation
             * Enregistrer par nous ***************************/
            double consommationCalculerParFactureStegetPeriodVisit = moyConsommationStegParJour * nombreJoursEntreVisites;
            double pourcentage = 0;
            if (consommationCalculerParFactureStegetPeriodVisit > consommationMesurerParTechnicienOrange){
                pourcentage = 100 - ( consommationMesurerParTechnicienOrange * 100 / consommationCalculerParFactureStegetPeriodVisit );
            }else{
                pourcentage = 100 - ( consommationCalculerParFactureStegetPeriodVisit  * 100 /  consommationMesurerParTechnicienOrange);
            }
            System.out.println("Pourcentage calculer = "+pourcentage);

            /******************** End Calculer le pourcentage d'erreur entre la consommation enregistrer par STEG et la Consommation
             * Enregistrer par nous ***************************/

            /** Si le pourcentage  > 20% enregistrer une alerte dans la bd**/
            if(pourcentage > 20){
                enregistrerAlert(
                        consommationMesurerParTechnicienOrange,
                        consommationCalculerParFactureStegetPeriodVisit,
                        pourcentage,
                        siteId
                );
            }
        }
    }

    @Override
    public List<AlertVisit> getAllAlerts() {
        return this.alertVisitRepository.findAll();
    }




    /**
     *
     * @param facturesPeriodVisit
     * @return double
     * Méthode calcul le moyenne de consommation par jour entre les factures de la part de la steg
     */
    public double calculMoyConsommationStegParJours(List<InvoiceItems> facturesPeriodVisit){
        long nombreJoursEntreFactures = 0;
        if (facturesPeriodVisit.size() > 1) {
            Date dateDebutFactures = facturesPeriodVisit.get(0).getItemDate();
            Date dateFinFactures = facturesPeriodVisit.get(facturesPeriodVisit.size() - 1).getItemDate();
            nombreJoursEntreFactures = ChronoUnit.DAYS.between(dateDebutFactures.toInstant(), dateFinFactures.toInstant());
        }else{
            return 0;
        }
        System.out.println("nombre de jours entre facture steg = "+ nombreJoursEntreFactures);

        // calculer la somme de consommation des factures
        double sommeConsommationSteg = 0;
        for (int i = 0; i < facturesPeriodVisit.size(); i++){
            sommeConsommationSteg += facturesPeriodVisit.get(i).getConsumptionKwh();
        }
        return sommeConsommationSteg / nombreJoursEntreFactures;
    }


    /**
     *
     * @param facturesPeriodVisit
     * @param dateVisitOne
     * @param dateVisitTwo
     * @param siteId
     * @return List<InvoiceItems>
     *     Si la liste des factures dans la période entre deux visites manque plus de 20 jours on ajoute
     *     deux factures aux bornes de la période pour mieux préciser l'estimation de la consommation
     */
    public List<InvoiceItems> traitementFactures(List<InvoiceItems> facturesPeriodVisit, Date dateVisitOne, Date dateVisitTwo, int siteId){
        // si le nombre de jour entre premiere visite et date premiere facture < 10 jrs => mé njibouch lfacture eli 9balha
        // si le nombre de jour entre derniere visite et date derniere facture < 10 jrs => mé njibouch fatoura o5ra
        Date datePremierFactureDansLaListe = facturesPeriodVisit.get(0).getItemDate();
        Date dateDerniereFactureDansLaListe = facturesPeriodVisit.get(facturesPeriodVisit.size()-1).getItemDate();
        System.out.println("derniére facture ddans la liste id = "+ facturesPeriodVisit.get(facturesPeriodVisit.size()-1).getItemId());
        long diffJourPVisitAndPFacture = Math.abs(ChronoUnit.DAYS.between(
                dateVisitOne.toInstant(),
                datePremierFactureDansLaListe.toInstant()));
        System.out.println("Nombre de jours entre premiere visite et premiere facture dans la liste = "+diffJourPVisitAndPFacture);
        long diffJourDVisitAndDFacture = Math.abs(ChronoUnit.DAYS.between(
                dateVisitTwo.toInstant(),
                dateDerniereFactureDansLaListe.toInstant()));
        System.out.println("Nombre de jours entre derniére visite et derniére facture dans la liste = "+diffJourDVisitAndDFacture);


        if(diffJourPVisitAndPFacture < 5){
            facturesPeriodVisit.remove(0);
        }


        if(diffJourDVisitAndDFacture > 20){
            //ajouter une facture si la différence entre derniere facture et deuxiéme visite > 15jrs
            InvoiceItems lastInvoiceAfter = this.invoiceItemService.findLastInvoiceAfter(dateDerniereFactureDansLaListe, siteId);
            if (lastInvoiceAfter != null){
                facturesPeriodVisit.add(lastInvoiceAfter);
            }
        }
        return facturesPeriodVisit;
    }




    private void enregistrerAlert(
            double consommationMesurerParTechnicienOrange,
            double consommationCalculerParFactureStegetPeriodVisit,
            double pourcentage,
            int siteId) {

        Site site = this.siteService.findSiteBySiteId(siteId);
        AlertTypes alertTypes= new AlertTypes((short) 2,"Surconsommation d’énergie");
        AlertVisit alertVisit= new AlertVisit();
        alertVisit.setRequiredConsommation(consommationMesurerParTechnicienOrange);
        alertVisit.setStegEstConsommation(consommationCalculerParFactureStegetPeriodVisit);
        alertVisit.setSite(site);
        alertVisit.setDifference(pourcentage);
        alertVisit.setAlertTypes(alertTypes);
        this.alertVisitRepository.save(alertVisit);
    }


    @Override
    public void alertTypeFactureHandler() {
        List<Site> sites = this.siteService.findAll();
        for (Site site : sites){
            int derniereFactureReelle = this.invoiceItemService.derniereFactureReel(site.getSiteId());
            if(derniereFactureReelle == 0){
                System.out.println("Site "+site.getSiteCode()+" est sans facture rélle");

                AlertTypes alertTypes= new AlertTypes((short) 3,"Site sans facturation réelle");
                AlertVisit ifAlertExist = this.alertVisitRepository.findFirstByAlertTypes_AlertTypeIdAndSite_SiteId(alertTypes.getAlertTypeId(), site.getSiteId());
                if(ifAlertExist == null){
                    AlertVisit alertVisit= new AlertVisit();
                    alertVisit.setSite(site);
                    alertVisit.setAlertTypes(alertTypes);
                    this.alertVisitRepository.save(alertVisit);
                }

            }
        }
    }

    @Override
    public List<AlertVisit> filterAlerts(FilterAlertRequest filterAlertRequest) {

        Specification<AlertVisit> spec = Specification.where(null);

        if(filterAlertRequest.getType() != 3){
            int type = 2;
            if(filterAlertRequest.getType() == 1){
                type = 3;
            }else{
                type = filterAlertRequest.getType();
            }
            spec = spec.and(FilterAlertSpecification.hasType(type));
        }


        if(filterAlertRequest.getDateAlert() != null){
            spec = spec.and(FilterAlertSpecification.hasDateAlert(filterAlertRequest.getDateAlert()));
        }

        if(filterAlertRequest.getCodeSite() != null){
            spec = spec.and(FilterAlertSpecification.hasCodeSite(filterAlertRequest.getCodeSite()));
        }


        if(filterAlertRequest.getPourcentage() != 0){
            spec = spec.and(FilterAlertSpecification.hasPourcentage(filterAlertRequest.getPourcentage()));
        }

        return this.alertVisitRepository.findAll(spec);
    }

    @Override
    public Map<String, Integer> distributionTypeOfAlert() {
        int nombreAlertSurconsommation = this.alertVisitRepository.countAlertVisitByAlertTypes_AlertTypeId(2);
        int nombreAlertFactureReelle = this.alertVisitRepository.countAlertVisitByAlertTypes_AlertTypeId(3);
        Map<String, Integer> map = new HashMap<>();
        map.put("surconsommation", nombreAlertSurconsommation);
        map.put("sans_facture_reelle", nombreAlertFactureReelle);
        return map;
    }
}
