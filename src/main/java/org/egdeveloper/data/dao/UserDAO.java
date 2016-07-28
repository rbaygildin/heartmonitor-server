package org.egdeveloper.data.dao;

import org.egdeveloper.data.model.Confident;
import org.egdeveloper.data.model.HealthStat;
import org.egdeveloper.data.model.SecurityToken;
import org.egdeveloper.data.model.User;
import org.egdeveloper.service.IKeyGenerator;
import org.egdeveloper.utils.exceptions.AuthorizationException;
import org.egdeveloper.utils.exceptions.UserNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class UserDAO implements IUserDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private IKeyGenerator tokenGenerator;

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
            session.clear();
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
            if(user == null)
                throw new UserNotFoundException();
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
            session.clear();
            session.close();
        }
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            User prevUser = (User) session.get(User.class, user.getId());
            prevUser.copyFrom(user);
            session.update(prevUser);
            tx.commit();
        }
        catch (Exception e){
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
        finally {
            session.clear();
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
            session.clear();
            session.close();
        }
    }

    @Override
    public Map authUser(String login, String password) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            User user = (User) session.createQuery("from User where login = :login and password = :password")
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .uniqueResult();
            if(user == null)
                throw new AuthorizationException();
            SecurityToken token = (SecurityToken) tokenGenerator.generate();
            token.setUser(user);
            session.save(token);
            user.getSecTokens().add(token);
            session.update(user);
            Map userCred = new HashMap<>();
            userCred.put("user", user);
            userCred.put("token", token.getGuid());
            tx.commit();
            return userCred;
        }
        catch (Exception e){
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
        finally {
            session.clear();
            session.close();
        }
    }

    @Override
    public Confident addConfident(Long userID, Confident confident) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            User user = (User) session.get(User.class, userID);
            if(user == null)
                throw new AuthorizationException();
            session.save(confident);
            user.getConfidents().add(confident);
            session.update(user);
            tx.commit();
            return confident;
        }
        catch (Exception e){
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
        finally {
            session.clear();
            session.close();
        }
    }

    public Collection<SecurityToken> findTokens(){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<SecurityToken> tokens = session.createQuery("from SecurityToken")
                    .list();
            tx.commit();
            return tokens;
        }
        catch (Exception e){
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
        finally {
            session.clear();
            session.close();
        }
    }

    public Collection<User> findUsers(){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<User> users = session.createQuery("from User")
                    .list();
            tx.commit();
            return users;
        }
        catch (Exception e){
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
        finally {
            session.clear();
            session.close();
        }
    }

    @Override
    public void registerDevice(User user, SecurityToken token) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(token);
            user.getSecTokens().add(token);
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
            session.clear();
            session.close();
        }
    }

    @Override
    public void addHealthStat(Long userId, HealthStat stat) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            User prevUser = (User) session.get(User.class, userId);
            prevUser.getHealthStat().add(stat);
            session.update(prevUser);
            tx.commit();
        }
        catch (Exception e){
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
        finally {
            session.clear();
            session.close();
        }
    }
}
