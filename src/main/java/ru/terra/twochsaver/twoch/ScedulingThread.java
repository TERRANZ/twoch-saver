package ru.terra.twochsaver.twoch;

import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


/**
 * Date: 07.05.14
 * Time: 1:57
 */
public class ScedulingThread implements Runnable {

    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void run() {
        logger.info("Starting timers manager...");
        try {
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler sched = sf.getScheduler();
            JobDetail job = JobBuilder.newJob(DownloadJob.class)
                    .withIdentity("DownloadJob", "group1")
                    .build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(
                            SimpleScheduleBuilder.simpleSchedule()
                                    .withIntervalInMinutes(5)
                                    .repeatForever()
                    )
                    .build();
            sched.scheduleJob(job, trigger);
            sched.start();
        } catch (SchedulerException se) {
            se.printStackTrace();
            logger.error("Error while initializing quartz", se);
        }
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
