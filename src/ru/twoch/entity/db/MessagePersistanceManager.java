package ru.twoch.entity.db;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
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
        List<Integer> objects = helper.findObjectsByField(Constants.MESSAGE_TEMPLATE_ID, Constants.MESSAGE_PARENT, parentId);
        if (objects.isEmpty())
        {
            return null;
        }
        List<Message> msgs = new ArrayList<Message>();
        for (Integer oid : objects)
        {
            msgs.add(helper.loadObject(Message.class, oid));
        }
        return msgs;
    }

    public Message findById(Long id)
    {
        List<Integer> objects = helper.findObjectsByField(Constants.MESSAGE_TEMPLATE_ID, Constants.MESSAGE_NUM, id);
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
