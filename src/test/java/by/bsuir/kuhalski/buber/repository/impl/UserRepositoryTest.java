package by.bsuir.kuhalski.buber.repository.impl;

import by.bsuir.kuhalski.buber.AppConfiguration;
import by.bsuir.kuhalski.buber.model.Role;
import by.bsuir.kuhalski.buber.model.User;
import by.bsuir.kuhalski.buber.repository.Repository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = {AppConfiguration.class})
public class UserRepositoryTest {
    PasswordEncoder encoder = new BCryptPasswordEncoder(10);
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository repository;

    @Test
    public void mustReturnAllUsers() {

        List<User> list = repository.queryAll();

        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void mustAddNewUser() {
        User newUser = new User();
        newUser.setLogin("Boris");
        newUser.setEnabled(true);
        newUser.setPassword(encoder.encode("12345"));

        List<Role> roles = roleRepository.queryAll();
        Set<Role> roleSet = new HashSet<Role>();
        roleSet.add(roles.get(0));

        newUser.setRoles(roleSet);
        repository.save(newUser);


        List<User> list = repository.queryAll();
        for (User user : list) {
            System.out.println(user);
        }
    }
}
