package org.example.dao;

import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        Session session = sessionFactory.withOptions().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> listUsers() {
        Session session = sessionFactory.openSession();
        try {
            Query<User> query = session.createQuery("FROM User", User.class);
            return query.getResultList();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getUsersByCarModelAndSeries(String carModel, int carSeries) {
        Session session = sessionFactory.openSession();
        try {
            String hql = "SELECT u FROM User u JOIN u.car c WHERE c.model = :carModel AND c.year = :carYear";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("carModel", carModel);
            query.setParameter("carYear", carSeries);
            return query.getResultList();
        } finally {
            session.close();
        }
    }
}
