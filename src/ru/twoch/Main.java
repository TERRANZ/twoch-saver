package ru.twoch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.terraobjects.entity.dao.TOObjectsManager;
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
	try
	{
	    String driverName = "com.mysql.jdbc.Driver";

	    Class.forName(driverName);

	    // Create a connection to the database
	    String serverName = "127.0.0.1";
	    String mydatabase = "terraobjects";
	    String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
	    String username = "scan";
	    String password = "scan";

	    Connection connection = DriverManager.getConnection(url, username, password);
	    if (connection != null)
	    {
		MainWindow mw = new MainWindow(connection);
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

	    } else
	    {
		System.out.println("Can't connect to db!");
	    }
	} catch (SQLException ex)
	{
	    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
	} catch (ClassNotFoundException ex)
	{
	    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
}
