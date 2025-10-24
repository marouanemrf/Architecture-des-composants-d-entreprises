package ma.projet.services;

import ma.projet.ado.IDao;
import ma.projet.beans.Femme;
import ma.projet.beans.Mariage;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class FemmeService implements IDao<Femme> {
    @Override
    public boolean save(Femme entity) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Femme entity) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Femme entity) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(entity);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Femme findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Femme.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Femme> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Femme ", Femme.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public long getChildrenCountBetweenDates(int femmeId, Date startDate, Date endDate) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String sql = "SELECT SUM(m.nbrEnfant) " +
                    "FROM Mariage m " +
                    "WHERE m.femme_id = :femmeId " +
                    "AND m.dateDebut BETWEEN :startDate AND :endDate " +
                    "AND (m.dateFin IS NULL OR m.dateFin >= :startDate)";

            NativeQuery<Long> query = session.createNativeQuery(sql);
            query.setParameter("femmeId", femmeId);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);

            Long result = query.getSingleResult();
            return result != null ? result : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Femme> findWomenMarriedAtLeastTwice() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT m.femme FROM Mariage m " +
                    "GROUP BY m.femme.id " +
                    "HAVING COUNT(m.id) >= 2";

            Query<Femme> query = session.createQuery(hql, Femme.class);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
