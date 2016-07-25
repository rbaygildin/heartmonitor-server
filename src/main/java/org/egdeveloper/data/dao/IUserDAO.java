package org.egdeveloper.data.dao;

import org.egdeveloper.data.model.User;

public interface IUserDAO {
    User createUserAccount(User user);
    void deleteUserAccount(Long userId);
    void updateUser(User user);
    User findUserById(Long id);
    User authUser(String login, String password);
}
