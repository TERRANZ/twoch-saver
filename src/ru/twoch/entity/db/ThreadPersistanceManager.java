package ru.twoch.entity.db;

import java.sql.Connection;
import java.util.List;
import ru.terraobjects.entity.TOPropertyType;
import ru.terraobjects.entity.dao.TOObjectsHelper;
import ru.twoch.entity.constants.Constants;

/**
 *
 * @author terranz
 */
public class ThreadPersistanceManager
{
    private TOObjectsHelper<Thread> helper;

    public ThreadPersistanceManager(Connection conn)
    {
	helper = new TOObjectsHelper(conn);
    }

    public Thread findById(Integer id)
    {
	List<Integer> objects = helper.findObjectsByField(Constants.THREAD_TEMPLATE_ID, Constants.THREAD_ID, id, TOPropertyType.TYPE_INT);
	if (objects.isEmpty())
	{
	    return null;
	}
	return helper.loadObject(Thread.class, objects.get(0));
    }

    public void insert(Thread t)
    {
	helper.storeObject(t, true);
    }
}
