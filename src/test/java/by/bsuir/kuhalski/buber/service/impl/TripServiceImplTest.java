package by.bsuir.kuhalski.buber.service.impl;

import by.bsuir.kuhalski.buber.AppConfiguration;
import by.bsuir.kuhalski.buber.model.Discount;
import by.bsuir.kuhalski.buber.model.Trip;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = {AppConfiguration.class})
public class TripServiceImplTest {

    @Autowired
    private TripService service;
    @Test
    public void mustDeleteTrip(){
        Optional<Trip> trip2 = service.loadEntityById(2);
        service.delete(trip2.get());

     //   Optional<Trip> trip = service.loadEntityById(1);
        List<Trip> trips = service.loadAllEntities();
        for (Trip trip1 : trips){
            System.out.println(trip1);
        }
   //     Assert.assertEquals(false,trip.isPresent());

    }
}
