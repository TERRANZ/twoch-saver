package ru.twoch.entity;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.twoch.HibernateUtil;

/**
 *
 * @author terranz
 */
public abstract class PersistanceManager
{
    protected Session session = HibernateUtil.getSessionFactory().openSession();

    public List<Object> findAll(Class entity)
    {
        Criteria c = session.createCriteria(entity);
        return c.list();
    }

    public void delete(Object o)
    {
        try
        {
            session.beginTransaction();
            session.delete(o);
            session.getTransaction().commit();
        } catch (HibernateException he)
        {
            he.printStackTrace();
        }
    }

    public void update(Object o)
    {
        try
        {
            //session.beginTransaction();
            session.update(o);
            session.flush();
        } catch (HibernateException he)
        {
            he.printStackTrace();
        }
    }

    public void insert(Object o)
    {
	try
        {
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
        } catch (HibernateException he)
        {
            he.printStackTrace();
        }
    }

    public abstract Object findById(Long id);
}
