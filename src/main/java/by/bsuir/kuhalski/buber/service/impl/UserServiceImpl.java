package by.bsuir.kuhalski.buber.service.impl;

import by.bsuir.kuhalski.buber.model.Discount;
import by.bsuir.kuhalski.buber.model.Role;
import by.bsuir.kuhalski.buber.model.User;
import by.bsuir.kuhalski.buber.repository.Repository;
import by.bsuir.kuhalski.buber.repository.specification.Specification;
import by.bsuir.kuhalski.buber.repository.specification.impl.SpecificationByRole;
import by.bsuir.kuhalski.buber.repository.specification.impl.SpecificationByLogin;
import by.bsuir.kuhalski.buber.repository.specification.impl.SpecificationByLoginAndPassword;
import by.bsuir.kuhalski.buber.repository.specification.impl.SpecificationByRoleId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Autowired
    private RoleService roleService;
    @Autowired
    private DiscountService discountService;
    @Autowired
    private TripService tripService;
    private PasswordEncoder encoder;

    protected UserServiceImpl(@Qualifier("userRepository") Repository repository) {
        encoder = new BCryptPasswordEncoder(10);
        this.repository = repository;
    }

    @Override
    public long findUserIdByUserNameAndPassword(String username, String password) {
        Specification specification = new SpecificationByLoginAndPassword(username, password);
        List<User> users = repository.queryAll();
        Optional<User> optional = repository.queryForSingleResult(specification);
        if (optional.isPresent()) {
            User user = optional.get();
            log.info("user was found ");
            return user.getId();
        } else {
            log.info("user with login " + username + " and password " + password + " was not found ");
            return 0;
        }
    }

    @Override
    public Optional<User> findByUserName(String username) {
        Specification specification = new SpecificationByLogin(username);
        return repository.queryForSingleResult(specification);
    }

    @Override
    public List<User> findUsersBuRoleWithLimit(String userRole, int limit, int offset) {
        Optional<Role> role = roleService.findRoleByRole(userRole);
        List<User> resultUsers = new ArrayList<>();
        if (role.isPresent()) {
            Specification specification = new SpecificationByRoleId(role.get().getId());
            List list = repository.queryBySpecificationWithLimit(specification, limit, offset);
            resultUsers.addAll(list);
        }
        return resultUsers;
    }

    @Override
    public void disableUserById(long userId) {
        Optional<User> optional = repository.queryById(userId);
        if (optional.isPresent()) {
            User user = optional.get();
            user.setEnabled(false);
            repository.save(user);
        }

    }

    @Override
    public void enableUserById(long userId) {
        Optional<User> optional = repository.queryById(userId);
        if (optional.isPresent()) {
            User user = optional.get();
            user.setEnabled(true);
            repository.save(user);
        }
    }

    @Override
    public void addUser(String role, String login, String password) {
        Optional<Role> roleByRole = roleService.findRoleByRole(role);
        if (roleByRole.isPresent()) {
            Role roleValue = roleByRole.get();
            User newUser = new User();
            newUser.setLogin(login);
            newUser.setPassword(encoder.encode(password));
            Set<Role> set = new HashSet<>();
            set.add(roleValue);
            newUser.setRoles(set);
            newUser.setEnabled(true);

            Set<Discount> discounts = new HashSet<>();
            discounts.add(discountService.loadEntityById(1).get());
            newUser.setDiscounts(discounts);
            repository.save(newUser);
        }
    }

    @Override
    public List<User> findAllEmptyDrivers() {
        List<User> drivers = findUsersBuRole("ROLE_DRIVER");

        Iterator<User> iterator = drivers.iterator();

        while (iterator.hasNext()) {
            User next = iterator.next();
            boolean isEmpty = tripService.driverIsEmpty(next);
            if (!isEmpty) {
                iterator.remove();
            }
        }
        return drivers;
    }

    @Override
    public List<User> findUsersBuRole(String role) {
        Optional<Role> roleByRole = roleService.findRoleByRole(role);
        List<User> users = new ArrayList<>();
        if (roleByRole.isPresent()) {
            Role roleValue = roleByRole.get();
            Specification specification = new SpecificationByRoleId(roleValue.getId());
            users.addAll(repository.query(specification));
        }
        return users;
    }
}
