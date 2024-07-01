package com.orange.orangegrs.utils.schedule;

import com.orange.orangegrs.entities.AlertJobs;
import com.orange.orangegrs.entities.Visite;
import com.orange.orangegrs.services.AlertJobsService;
import com.orange.orangegrs.services.AlertVisitService;
import com.orange.orangegrs.services.VisiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {


    @Autowired
    private AlertVisitService alertVisitService;


    @Autowired
    private AlertJobsService alertJobsService;


    @Autowired
    private VisiteService visiteService;

    @Scheduled(cron = "0 0 19 * * ?")
    public void executeTask() {
        System.out.println("Vérification de consommation en cours d'éxécution.");
        AlertJobs alertJobs = alertJobsService.getAlertJobs((short) 1);
        if(alertJobs.isActive()){
            List<Visite> listVisit = this.visiteService.findVisitAddedToDay(new Date());
            if(listVisit != null){
                for (Visite visite: listVisit){
                    if(visite.getSite().getStatusId().getDescription() != "Résilié"){
                        alertVisitService.alertHandler(visite.getSite().getSiteId());
                    }
                }
            }
        }
        this.alertJobsService.updateAlertJobsActivation((short) 1, false);
        System.out.println("Vérification de consommation est terminé.");
    }

    @Scheduled(cron = "0 0 17 ? * SAT")
    public void executeTaskInvoiceType() {
        System.out.println("La tâche programmée s'exécute chaque samedi à 17h. Alert Site Sans Factures");
        this.alertVisitService.alertTypeFactureHandler();
        System.out.println("Fin tâche Alert Site Sans Factures");
    }
}
