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
        if (args.length == 0)
        {
            MainWindow mw = new MainWindow();
            mw.setVisible(true);
        } else
        {
            Boolean img = true;
            String board = "";
            for (int i = 0; i < args.length; i++)
            {
                if ("-noimg".equals(args[i]))
                {
                    img = false;
                } else
                {
                    board = args[i];
                }
            }
            WorkerThread wt = new WorkerThread(board, null, img);
            Thread t = new Thread(wt);
            t.start();
        }
//                TOTemplateHelper helper = new TOTemplateHelper(connection);
//                try
//                {
//                    helper.createTemplateFromClass(Message.class);
//                    helper.createTemplateFromClass(ru.twoch.entity.db.Thread.class);
//                } catch (InstantiationException ex)
//                {
//                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IllegalAccessException ex)
//                {
//                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//                }


    }
}
