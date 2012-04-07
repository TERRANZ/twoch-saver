package ru.twoch.entity.db;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import ru.terraobjects.entity.TOObject;
import ru.terraobjects.entity.TOPropertyType;
import ru.terraobjects.entity.dao.TOObjectsHelper;
import ru.twoch.entity.constants.Constants;

/**
 *
 * @author terranz
 */
public class MessagePersistanceManager
{
    private TOObjectsHelper<Message> helper;

    public MessagePersistanceManager(Connection c)
    {
        helper = new TOObjectsHelper(c);
    }

    public List<Message> findMessagesByParent(Long parentId)
    {
        List<TOObject> objects = helper.findObjectsByField(Constants.MESSAGE_TEMPLATE_ID, Constants.MESSAGE_PARENT, parentId, TOPropertyType.TYPE_INT);
        if (objects.isEmpty())
        {
            return null;
        }
        List<Message> msgs = new ArrayList<Message>();
        for (TOObject o : objects)
        {
            msgs.add(helper.loadObject(Message.class, o));
        }
        return msgs;
    }

    public Message findById(Integer id)
    {
        List<TOObject> objects = helper.findObjectsByField(Constants.MESSAGE_TEMPLATE_ID, Constants.MESSAGE_NUM, id, TOPropertyType.TYPE_INT);
        if (objects.isEmpty())
        {
            return null;
        }
        return helper.loadObject(Message.class, objects.get(0));
    }

    public void insert(Message m)
    {
        helper.storeObject(m, true);
    }
}
