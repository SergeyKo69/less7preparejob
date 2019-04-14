package ru.kogut.enterprise.services.interfaces;

import ru.kogut.enterprise.model.dictionary.User;

import java.util.List;

/**
 * @author S.Kogut on 2019-04-11
 */

public interface UserInt {

    User createUser(User user);

    User updateUser(User user);

    List<User> findAllUsers();

    User findByLoginAndPassword(String login, String password);

    User findUserById(String id);

    User findByUserName(String userName);

    void deleteUser(User user);

    void deleteUserById(String id);

}
