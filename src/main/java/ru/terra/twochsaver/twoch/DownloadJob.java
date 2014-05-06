package ru.terra.twochsaver.twoch;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.terra.twochsaver.Main;

/**
 * Date: 07.05.14
 * Time: 2:01
 */
public class DownloadJob implements Job {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Starting download job");
        WorkerThread wt = new WorkerThread(Main.board, params -> Runtime.getRuntime().exit(0), Main.img);
        Thread t = new Thread(wt);
        t.start();
    }
}
