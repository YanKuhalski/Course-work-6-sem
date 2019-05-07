package by.bsuir.kuhalski.buber.service.impl;

import by.bsuir.kuhalski.buber.model.User;
import by.bsuir.kuhalski.buber.repository.impl.UserRepository;
import by.bsuir.kuhalski.buber.repository.specification.Specification;
import by.bsuir.kuhalski.buber.repository.specification.impl.SpecificationByLogin;
import by.bsuir.kuhalski.buber.repository.specification.impl.SpecificationByLoginAndPassword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Autowired
    private UserRepository repository;

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
    public  Optional<User> findByUserName(String username) {
        Specification specification = new SpecificationByLogin(username);
        return repository.queryForSingleResult(specification);
    }
}
