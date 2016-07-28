package org.egdeveloper.service;


import org.egdeveloper.data.model.Confident;
import org.egdeveloper.data.model.SecurityToken;
import org.egdeveloper.data.model.User;

import java.util.Collection;
import java.util.Map;

public interface IUserService {
    User createUserAccount(User user);
    void deleteUserAccount(Long userId);
    void updateUser(User user);
    User findUserById(Long userId);
    Confident addConfident(Long userId, Confident confident);
    Collection<Confident> findConfident(Long userId);
    Map authUser(String login, String password);
    User findUserByToken(String token);
    void registerDevice(User user, SecurityToken token);
}
