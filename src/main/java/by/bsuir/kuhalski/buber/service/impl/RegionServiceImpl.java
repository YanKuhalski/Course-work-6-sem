package by.bsuir.kuhalski.buber.service.impl;

import by.bsuir.kuhalski.buber.model.Region;
import by.bsuir.kuhalski.buber.repository.Repository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImpl extends AbstractService<Region> implements RegionService {
    protected RegionServiceImpl(@Qualifier("regionRepository") Repository repository) {
        this.repository = repository;
    }
}
