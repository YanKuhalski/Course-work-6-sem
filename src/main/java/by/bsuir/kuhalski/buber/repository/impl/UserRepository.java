package by.bsuir.kuhalski.buber.repository.impl;


import by.bsuir.kuhalski.buber.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository extends AbstractRepository<User> {

    private static final String DATABASE_NAME = " users ";

    @Override
    protected String getBaseName() {
        return DATABASE_NAME;
    }

    @Override
    public List<User> queryAll() {
        return entityManager.createNamedQuery("queryAllUsers").getResultList();
    }
}
