package by.bsuir.kuhalski.buber.service.impl;

import by.bsuir.kuhalski.buber.model.Role;
import by.bsuir.kuhalski.buber.repository.Repository;
import by.bsuir.kuhalski.buber.repository.specification.Specification;
import by.bsuir.kuhalski.buber.repository.specification.impl.SpecificationByRole;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RoleServiceImpl extends  AbstractService<Role> implements  RoleService {
    protected RoleServiceImpl(@Qualifier("roleRepository") Repository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Role> findRoleByRole(String role) {
        Specification specification = new SpecificationByRole(role);
     return  repository.queryForSingleResult(specification);

    }
}
