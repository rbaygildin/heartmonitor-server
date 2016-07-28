package org.egdeveloper.data.dao;

import org.egdeveloper.data.model.Confident;
import org.egdeveloper.data.model.HealthStat;
import org.egdeveloper.data.model.SecurityToken;
import org.egdeveloper.data.model.User;

import java.util.Collection;
import java.util.Map;

public interface IUserDAO {
    User createUserAccount(User user);
    void deleteUserAccount(Long userId);
    void updateUser(User user);
    User findUserById(Long id);
    Map authUser(String login, String password);
    Confident addConfident(Long userID, Confident confident);
    Collection<SecurityToken> findTokens();
    Collection<User> findUsers();
    void registerDevice(User user, SecurityToken token);
    void addHealthStat(Long userId, HealthStat stat);
}
