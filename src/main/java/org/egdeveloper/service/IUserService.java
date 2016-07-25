package org.egdeveloper.service;


import org.egdeveloper.data.model.Confident;
import org.egdeveloper.data.model.User;

import java.util.Collection;

public interface IUserService {
    User createUserAccount(User user);
    void deleteUserAccount(Long userId);
    void updateUser(User user);
    User findUserById(Long userId);
    User addConfident(Long userId, Confident confident);
    Collection<Confident> findConfidents(Long userId);
    User authUser(String login, String password);
}
