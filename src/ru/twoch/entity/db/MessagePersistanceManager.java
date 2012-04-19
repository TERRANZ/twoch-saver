package ru.twoch.entity.db;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author terranz
 */
public class MessagePersistanceManager extends PersistanceManager
{
    @Override
    public Object findById(Long id)
    {
        Criteria c = session.createCriteria(Message.class);
        c.add(Restrictions.eq("num", id));
        return c.uniqueResult();
    }

    public List<Message> findMessagesByParent(Long parentId)
    {
        Criteria c = session.createCriteria(Message.class);
        c.add(Restrictions.eq("parent", parentId));
        return c.list();
    }

    public Boolean isMessageExists(Long id)
    {
        return session.createCriteria(Message.class).add(Restrictions.eq("num", id)).uniqueResult() != null;
    }
}
