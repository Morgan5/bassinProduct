package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.security.auth.login.Configuration;
import org.hibernate.Query;

//Hibernate 3.0
public class HibernateDao {

    private SessionFactory sessionFactory;

    public <T> T create(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
        return entity;
    }

    public <T> T findById(Class<T> clazz, Serializable id) {
        Session session = sessionFactory.openSession();
        T entity = (T) session.get(clazz, id);
        session.close();
        return entity;
    }

    public <T> List<T> findAll(Class<T> tClass) {
        Session session = sessionFactory.openSession();
        List<T> results = session.createCriteria(tClass).list();
        session.close();
        return results;
    }

    public <T> List<T> findWhere(T entity) {
        Session session = sessionFactory.openSession();
        Example example = Example.create(entity).ignoreCase();
        List<T> results = session.createCriteria(entity.getClass()).add(example).list();
        session.close();
        return results;
    }

//    public <T> List<T> findWhereOr(String nom) {
//        Session session = sessionFactory.openSession();
//        List<T> results = new ArrayList<>();
//        try {
//            Query query = session.createQuery("from Sujet p where p.titre like '%" + nom + "%' or p.body like '%" + nom + "%'");
//
//            System.out.println(" nom ===>" + nom);
//            Iterator personnes = query.iterate();
//
//            while (personnes.hasNext()) {
//                Sujet personne = (Sujet) personnes.next();
//                System.out.println("nom = " + personne.getTitre());
//                results.add((T) personne);
//            }
//        } finally {
//            session.close();
//        }
//        return results;
//    }
    
//    public <T> List<T> findWhereOrValide() {
//        Session session = sessionFactory.openSession();
//        List<T> results = new ArrayList<>();
//        try {
//            Query query = session.createQuery("from Sujet p where p.etat=1");
//
//            Iterator personnes = query.iterate();
//
//            while (personnes.hasNext()) {
//                Sujet personne = (Sujet) personnes.next();
//                System.out.println("nom = " + personne.getTitre());
//                results.add((T) personne);
//            }
//        } finally {
//            session.close();
//        }
//        return results;
//    }
//  public <T> List<T> findWherenonValide() {
//        Session session = sessionFactory.openSession();
//        List<T> results = new ArrayList<>();
//        try {
//            Query query = session.createQuery("from Sujet p where p.etat=0");
//            System.out.print(query.toString());
//            Iterator personnes = query.iterate();
//
//            while (personnes.hasNext()) {
//                Sujet personne = (Sujet) personnes.next();
//                System.out.println("nom = " + personne.getTitre());
//                results.add((T) personne);
//            }
//        } finally {
//            session.close();
//        }
//        return results;
//    }
//public <T> List<T> findvalidemipoitra(int n) {
//        Session session = sessionFactory.openSession();
//        List<T> results = new ArrayList<>();
//        
//        try {
//            Query query = session.createQuery("from Sujet p where p.etat=1 order by id desc offset+"+n+"limit 3 ");
//            System.out.print(query.toString());
//            Iterator personnes = query.iterate();
//
//            while (personnes.hasNext()) {
//                Sujet personne = (Sujet) personnes.next();
//                System.out.println("nom = " + personne.getTitre());
//                results.add((T) personne);
//            }
//        } finally {
//            session.close();
//        }
//        return results;
//    }
//    public <T> List<T> paginateWhere(T entity, int offset, int size, String orderBy, boolean orderAsc) {
//        Session session = sessionFactory.openSession();
//        Order order = (orderAsc) ? Order.asc(orderBy) : Order.desc(orderBy);
//        Example example = Example.create(entity).ignoreCase();
//        List<T> results = session.createCriteria(entity.getClass())
//                .add(example)
//                .addOrder(order)
//                .setFirstResult(offset)
//                .setMaxResults(offset + size).list();
//        Sujet s = (Sujet) entity;
//        System.out.println(results.size());
//        System.out.println("type: "+s.getIdtype());
//        System.out.println("etat: "+s.getEtat());
//        session.close();
//        return results;
//    }

    public <T> List<T> paginate(Class<T> clazz, int offset, int size) {
        Session session = sessionFactory.openSession();
        List<T> results = session.createCriteria(clazz)
                .setFirstResult(offset)
                .setMaxResults(offset + size).list();
        session.close();
        return results;
    }

    public <T> List<T> paginate(Class<T> clazz, int offset, int size, String orderBy, boolean orderAsc) {
        Session session = sessionFactory.openSession();
        Order order = (orderAsc) ? Order.asc(orderBy) : Order.desc(orderBy);
        List<T> results = session.createCriteria(clazz)
                .addOrder(order)
                .setFirstResult(offset)
                .setMaxResults(size).list();
        session.close();
        return results;
    }

    public void deleteById(Class tClass, Serializable id) {
        delete(findById(tClass, id));
    }

    public void delete(Object entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    
    public <T> T update(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
        return entity;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
