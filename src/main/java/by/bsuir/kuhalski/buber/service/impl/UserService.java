package by.bsuir.kuhalski.buber.service.impl;


import by.bsuir.kuhalski.buber.model.User;
import by.bsuir.kuhalski.buber.service.Service;

import java.util.List;
import java.util.Optional;

public interface UserService extends Service<User> {
    long findUserIdByUserNameAndPassword(String username, String password);

    Optional<User> findByUserName(String username);

    List<User> findUsersBuRoleWithLimit(String role, int limit, int offset);
    List<User> findUsersBuRole (String role );

    void disableUserById(long userId);

    void enableUserById(long userId);

    void addUser(String role, String login, String password);

    List<User> findAllEmptyDrivers();
}
