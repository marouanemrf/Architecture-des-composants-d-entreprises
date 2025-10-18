package com.tp.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.tp.dao.IDao;
import com.tp.entities.Salle;
import com.tp.util.HibernateUtil;

import java.util.List;

public class SalleService implements IDao<Salle> {
    @Override
    public boolean create(Salle salle) {
        Session session = null;
        Transaction tx = null;
        boolean result = false;

        try {
            if (salle.getId() != null) {
                System.out.println("Generated ID: " + salle.getId());
            } else {
                System.out.println("ID was not generated.");
            }

            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(salle);
            tx.commit();
            result = true;

        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();

            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();

        }
        return result;
    }

    @Override
    public boolean update(Salle salle) {
        Session session = null;
        Transaction tx = null;
        boolean result = false;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(salle);
            tx.commit();
            result = true;
        } catch(HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }

        return result;
    }

    @Override
    public boolean delete(Salle salle) {
        Session session = null;
        Transaction tx = null;
        boolean result = false;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(salle);
            tx.commit();
            result = true;
        } catch(HibernateException e) {
            if (tx != null)
                tx.rollback();
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }

        return result;
    }

    @Override
    public Salle findById(int id) {
        Session session = null;
        Salle salle = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            salle = (Salle) session.get(Salle.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return salle;
    }


    @Override
    public List<Salle> findAll() {
        Session session = null;
        Transaction tx = null;
        List<Salle> salles = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            salles = (List<Salle>) session.createQuery("from Salle").list();
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("no salles found");
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return salles;
    }
}
