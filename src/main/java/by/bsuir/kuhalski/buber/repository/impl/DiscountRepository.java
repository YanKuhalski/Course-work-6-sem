package by.bsuir.kuhalski.buber.repository.impl;

import by.bsuir.kuhalski.buber.model.Discount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DiscountRepository extends AbstractRepository<Discount> {
    private static final String DATABASE_NAME = " discount ";

    @Override
    protected String getBaseName() {
        return DATABASE_NAME;
    }

    @Override
    public List<Discount> queryAll() {
        return entityManager.createNamedQuery("queryAllDiscounts").getResultList();
    }
}
