package ru.twoch;

import ru.twoch.gui.MainWindow;

/**
 *
 * @author terranz
 */
public class Main
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        System.out.println("Запуск...");
//        WorkerThread wt = new WorkerThread();
//        Thread t = new Thread(wt);
//        t.start();
        MainWindow mw = new MainWindow();
        mw.setVisible(true);
    }
}
