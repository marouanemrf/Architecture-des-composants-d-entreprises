package ma.projet.services;

import jakarta.persistence.criteria.*;
import ma.projet.ado.IDao;
import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class MariageService implements IDao<Mariage> {
    @Override
    public boolean save(Mariage entity) {
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
    public boolean update(Mariage entity) {
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
    public boolean delete(Mariage entity) {
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
    public Mariage findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Mariage.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Mariage> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Mariage", Mariage.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public long countMenMarriedToFourWomenBetweenDates(Date startDate, Date endDate) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Créer un CriteriaBuilder à partir de la session
            CriteriaBuilder cb = session.getCriteriaBuilder();

            // Crier une requête pour compter les hommes mariés
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Mariage> mariageRoot = cq.from(Mariage.class); // Définir l'entité principale (Mariage)

            // Joindre la table des hommes et des femmes (si nécessaire)
            Join<Mariage, Homme> hommeJoin = mariageRoot.join("homme", JoinType.INNER);

            // Ajouter les conditions pour filtrer les mariages entre les dates spécifiées
            Predicate startDatePredicate = cb.greaterThanOrEqualTo(mariageRoot.get("dateDebut"), startDate);
            Predicate endDatePredicate = cb.lessThanOrEqualTo(mariageRoot.get("dateDebut"), endDate);
            Predicate activeMarriagePredicate = cb.or(
                    cb.isNull(mariageRoot.get("dateFin")),
                    cb.greaterThanOrEqualTo(mariageRoot.get("dateFin"), startDate)
            );
            cq.where(cb.and(startDatePredicate, endDatePredicate, activeMarriagePredicate));

            // Grouper par homme et compter les mariages
            cq.select(cb.countDistinct(hommeJoin.get("id")));
            cq.groupBy(hommeJoin.get("id"));

            // Exécuter la requête
            long count = session.createQuery(cq).getSingleResult();

            // Retourner le nombre d'hommes mariés à quatre femmes
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Object[]> findMarriagesByMan(int hommeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Requête HQL pour récupérer les mariages d'un homme spécifique avec les détails
            String hql = "SELECT m.femme, m.dateDebut, m.dateFin, m.nbrEnfant " +
                    "FROM Mariage m " +
                    "WHERE m.homme.id = :hommeId";

            Query<Object[]> query = session.createQuery(hql, Object[].class);
            query.setParameter("hommeId", hommeId);

            // Exécuter la requête et retourner la liste des résultats
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();  // Retourner une liste vide en cas d'erreur
        }
    }

}
