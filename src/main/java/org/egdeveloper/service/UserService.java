package org.egdeveloper.service;

import org.egdeveloper.data.dao.IUserDAO;
import org.egdeveloper.data.model.Confident;
import org.egdeveloper.data.model.SecurityToken;
import org.egdeveloper.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Confident addConfident(Long userId, Confident confident) {
        return userDAO.addConfident(userId, confident);
    }

    @Override
    public Collection<Confident> findConfident(Long userId) {
        return userDAO.findUserById(userId).getConfidents();
    }

    @Override
    public Map authUser(String login, String password) {
        return userDAO.authUser(login, password);
    }

    @Override
    public User findUserByToken(String token) {
        return userDAO.findTokens().stream().filter(t -> t.getGuid().equals(token))
                .map(t -> t.getUser()).findFirst().orElse(null);
    }

    @Override
    public void registerDevice(User user, SecurityToken token) {
        userDAO.registerDevice(user, token);
    }
}
