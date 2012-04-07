package ru.twoch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.terraobjects.entity.manager.TOObjectsManager;
import ru.terraobjects.entity.dao.TOTemplateHelper;
import ru.twoch.entity.db.Message;
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

        MainWindow mw = new MainWindow();
        mw.setVisible(true);
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
