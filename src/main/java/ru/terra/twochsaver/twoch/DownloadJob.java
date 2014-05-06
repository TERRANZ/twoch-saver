package ru.terra.twochsaver.twoch;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import ru.terra.twochsaver.Main;

/**
 * Date: 07.05.14
 * Time: 2:01
 */
public class DownloadJob implements Job {
    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Starting download job");
        WorkerThread wt = new WorkerThread(Main.board, params -> Runtime.getRuntime().exit(0), Main.img);
        Thread t = new Thread(wt);
        t.start();
    }
}
