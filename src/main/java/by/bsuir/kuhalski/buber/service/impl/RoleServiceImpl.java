package by.bsuir.kuhalski.buber.service.impl;

import by.bsuir.kuhalski.buber.model.Role;
import by.bsuir.kuhalski.buber.repository.Repository;
import by.bsuir.kuhalski.buber.repository.impl.RoleRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;



@Service
public class RoleServiceImpl extends  AbstractService<Role> implements  RoleService {
    protected RoleServiceImpl(@Qualifier("roleRepository") Repository repository) {
        this.repository = repository;
    }
}
