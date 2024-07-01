package com.orange.orangegrs.repositories;

import com.orange.orangegrs.entities.AlertJobs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertJobsRepository extends JpaRepository<AlertJobs, Short> {
    AlertJobs findAlertJobsByAlertJobId(short alertJobsId);
}
