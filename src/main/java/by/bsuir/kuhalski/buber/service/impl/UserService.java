package by.bsuir.kuhalski.buber.service.impl;


import by.bsuir.kuhalski.buber.model.User;
import by.bsuir.kuhalski.buber.service.Service;

import java.util.Optional;
public interface UserService extends Service<User> {
    long findUserIdByUserNameAndPassword(String username, String password);
    Optional<User> findByUserName(String username);
}
