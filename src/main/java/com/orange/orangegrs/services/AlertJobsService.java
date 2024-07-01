package com.orange.orangegrs.services;

import com.orange.orangegrs.entities.AlertJobs;

public interface AlertJobsService {

    AlertJobs addNewAlertJob(AlertJobs alertJobs);


    AlertJobs updateAlertJobsActivation(short alertJobsId, boolean value);


    AlertJobs getAlertJobs(short alertJobsId);
}
