package by.bsuir.kuhalski.buber.repository.impl;

import by.bsuir.kuhalski.buber.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepository extends  AbstractRepository<Role> {

    @Override
    protected String getBaseName() {
        return " role ";
    }

    @Override
    public List<Role> queryAll() {
        return entityManager.createNamedQuery("queryAllRoles").getResultList();
    }
}
