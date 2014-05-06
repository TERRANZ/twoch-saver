package ru.terra.twochsaver;

import org.apache.log4j.BasicConfigurator;
import ru.terra.twochsaver.twoch.ScedulingThread;
import ru.terra.twochsaver.twoch.WorkIsDoneListener;
import ru.terra.twochsaver.twoch.WorkerThread;

/**
 * @author terranz
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static String board;
    public static Boolean img = true;

    public static void main(String[] args) {
        System.out.println("Запуск...");
        BasicConfigurator.configure();

        Boolean sched = false;
        for (int i = 0; i < args.length; i++) {
            if ("-noimg".equals(args[i])) {
                img = false;
            } else if ("-sched".equalsIgnoreCase(args[i]))
                sched = true;
            else {
                board = args[i];
            }

        }
        if (!sched) {
            WorkerThread wt = new WorkerThread(board, params -> Runtime.getRuntime().exit(0), img);
            Thread t = new Thread(wt);
            t.start();
        } else {
            ScedulingThread scedulingThread = new ScedulingThread();
            Thread t = new Thread(scedulingThread);
            t.start();
        }
    }
}
