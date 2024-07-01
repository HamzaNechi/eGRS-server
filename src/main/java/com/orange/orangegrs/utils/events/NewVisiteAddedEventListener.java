package com.orange.orangegrs.utils.events;

import com.orange.orangegrs.entities.Visite;
import com.orange.orangegrs.services.AlertJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class NewVisiteAddedEventListener implements ApplicationListener<NewVisiteAddedEvent> {

    @Autowired
    private AlertJobsService alertJobsService;


    @Override
    public void onApplicationEvent(NewVisiteAddedEvent event) {
        this.alertJobsService.updateAlertJobsActivation((short) 1, true);
    }
}
