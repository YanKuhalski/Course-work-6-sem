package by.bsuir.kuhalski.buber.repository.impl;

import by.bsuir.kuhalski.buber.AppConfiguration;
import by.bsuir.kuhalski.buber.model.Region;
import by.bsuir.kuhalski.buber.model.Role;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = {AppConfiguration.class})
public class RoleRepositoryTest {
    @Autowired
    private RoleRepository repository;
    @Test
    public void mustReturnAllRegions(){

        List<Role> list = repository.queryAll();

        for (Role entity : list){
            System.out.println(entity );
        }
    }
}
