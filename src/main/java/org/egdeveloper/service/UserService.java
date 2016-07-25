package org.egdeveloper.service;

import org.egdeveloper.data.dao.IUserDAO;
import org.egdeveloper.data.model.Confident;
import org.egdeveloper.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserDAO userDAO;

    @Override
    public User createUserAccount(User user) {
        return userDAO.createUserAccount(user);
    }

    @Override
    public void deleteUserAccount(Long userId) {
        userDAO.deleteUserAccount(userId);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public User findUserById(Long userId) {
        return userDAO.findUserById(userId);
    }

    @Override
    public User addConfident(Long userId, Confident confident) {
        User user = userDAO.findUserById(userId);
        user.getConfidents().add(confident);
        userDAO.updateUser(user);
        return user;
    }

    @Override
    public Collection<Confident> findConfidents(Long userId) {
        return userDAO.findUserById(userId).getConfidents();
    }

    @Override
    public User authUser(String login, String password) {
        return userDAO.authUser(login, password);
    }
}
