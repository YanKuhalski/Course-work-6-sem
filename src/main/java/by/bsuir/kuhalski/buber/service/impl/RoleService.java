package by.bsuir.kuhalski.buber.service.impl;

import by.bsuir.kuhalski.buber.model.Role;
import by.bsuir.kuhalski.buber.service.Service;

import java.util.Optional;

public interface RoleService extends Service<Role> {
    Optional<Role> findRoleByRole(String role);
}
