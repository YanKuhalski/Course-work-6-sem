package by.bsuir.kuhalski.buber.service.impl;

import by.bsuir.kuhalski.buber.model.Role;
import by.bsuir.kuhalski.buber.repository.Repository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl extends  AbstractService<Role> implements  RoleService{
    protected TripServiceImpl(@Qualifier("tripRepository") Repository repository) {
        this.repository = repository;
    }

}
