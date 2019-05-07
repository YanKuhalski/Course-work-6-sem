package by.bsuir.kuhalski.buber.service.impl;

import by.bsuir.kuhalski.buber.model.Discount;
import by.bsuir.kuhalski.buber.repository.Repository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl extends AbstractService<Discount> implements DiscountService {
    protected DiscountServiceImpl(@Qualifier("discountRepository") Repository repository) {
        this.repository = repository;
    }
}
