package ru.terra.twochsaver.twoch;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 07.05.14
 * Time: 1:57
 */
public class ScedulingThread implements Runnable {
    private Scheduler scheduler;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ScedulingThread() {
        logger.info("Starting timers manager...");
        try {
            // Grab the Scheduler instance from the Factory
            SchedulerFactory schFactory = new StdSchedulerFactory();
            scheduler = schFactory.getScheduler();
            startSessionsExpirationJob();
            scheduler.start();
            startSessionsExpirationJob();
        } catch (SchedulerException se) {
            se.printStackTrace();
            logger.error("Error while initializing quartz", se);
        }
    }

    private void startSessionsExpirationJob() throws SchedulerException {
        JobDetail job = JobBuilder.newJob(DownloadJob.class).withIdentity("DownloadJob").build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInMinutes(5)
                                .repeatForever()
                )
                .build();
        //scheduler.scheduleJob(job, trigger);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
