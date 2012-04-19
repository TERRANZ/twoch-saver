package ru.twoch.entity.db;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author terranz
 */
public class ThreadPersistanceManager extends PersistanceManager
{
    @Override
    public Object findById(Long id)
    {
        Criteria c = session.createCriteria(TThread.class);
        c.add(Restrictions.eq("startMessage", id));
        return c.uniqueResult();
    }
}
