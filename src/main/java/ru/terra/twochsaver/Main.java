package ru.terra.twochsaver;

import ru.terra.twochsaver.twoch.WorkIsDoneListener;
import ru.terra.twochsaver.twoch.WorkerThread;

/**
 * @author terranz
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Запуск...");
        Boolean img = true;
        String board = "";
        for (int i = 0; i < args.length; i++) {
            if ("-noimg".equals(args[i])) {
                img = false;
            } else {
                board = args[i];
            }
        }
        WorkerThread wt = new WorkerThread(board, new WorkIsDoneListener() {
            @Override
            public void done(String... params) {
                Runtime.getRuntime().exit(0);
            }
        }, img);
        Thread t = new Thread(wt);
        t.start();
    }
}
