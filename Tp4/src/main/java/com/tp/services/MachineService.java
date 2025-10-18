package com.tp.services;

import com.tp.entities.Machine;
import com.tp.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.tp.dao.IDao;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class MachineService implements IDao<Machine> {

    @Override
    public boolean create(Machine machine) {
        Session session = null;
        Transaction tx = null;
        boolean result = false;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            session.save(machine);

            tx.commit();
            result = true;

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            // Close the session
            if (session != null) {
                session.close();
            }
        }

        return result;
    }

    @Override
    public boolean update(Machine machine) {
        Session session = null;
        Transaction tx = null;
        boolean result = false;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            session.update(machine);

            tx.commit();
            result = true;

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            // Close the session
            if (session != null) {
                session.close();
            }
        }

        return result;
    }

    @Override
    public boolean delete(Machine machine) {
        Session session = null;
        Transaction tx = null;
        boolean result = false;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            session.delete(machine);

            tx.commit();
            result = true;

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return result;
    }

    @Override
    public Machine findById(int id) {
        Session session = null;
        Machine machine = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            machine = session.get(Machine.class, id);

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            // Close the session
            if (session != null) {
                session.close();
            }
        }

        return machine;
    }

    @Override
    public List<Machine> findAll() {
        Session session = null;
        List<Machine> machines = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            machines = session.createQuery("FROM Machine", Machine.class).list();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return machines;
    }

    public List<Machine> findBetweenDate(Date d1, Date d2) {
        Session session = null;
        List<Machine> machines = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createNamedQuery("findBetweenDate");
            query.setParameter("d1", d1);
            query.setParameter("d2", d2);

            machines = query.getResultList();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return machines;
    }

}
