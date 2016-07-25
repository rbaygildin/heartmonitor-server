package org.egdeveloper.data.dao;

import org.egdeveloper.data.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDAO implements IUserDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User createUserAccount(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            return user;
        }
        catch (Exception e){
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public void deleteUserAccount(Long userId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            User user = (User) session.get(User.class, userId);
            session.delete(user);
            tx.commit();
        }
        catch (Exception e){
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        }
        catch (Exception e){
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public User findUserById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            User user = (User) session.get(User.class, id);
            tx.commit();
            return user;
        }
        catch (Exception e){
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public User authUser(String login, String password) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            User user = (User) session.createQuery("from User where login = :login and password = :password")
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .uniqueResult();
            tx.commit();
            return user;
        }
        catch (Exception e){
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
        finally {
            session.close();
        }
    }
}
