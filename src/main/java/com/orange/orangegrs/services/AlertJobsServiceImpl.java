package com.orange.orangegrs.services;

import com.orange.orangegrs.entities.AlertJobs;
import com.orange.orangegrs.repositories.AlertJobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AlertJobsServiceImpl implements AlertJobsService{


    @Autowired
    private AlertJobsRepository alertJobsRepository;



    @Override
    public AlertJobs addNewAlertJob(AlertJobs alertJobs) {
        return this.alertJobsRepository.save(alertJobs);
    }

    @Override
    public AlertJobs updateAlertJobsActivation(short alertJobsId, boolean value) {
        AlertJobs alertJobs = this.alertJobsRepository.findAlertJobsByAlertJobId(alertJobsId);
        if(alertJobs != null){
            alertJobs.setActive(value);
            return this.alertJobsRepository.save(alertJobs);
        }
        return null;
    }

    @Override
    public AlertJobs getAlertJobs(short alertJobsId) {
        return this.alertJobsRepository.findAlertJobsByAlertJobId(alertJobsId);
    }
}
