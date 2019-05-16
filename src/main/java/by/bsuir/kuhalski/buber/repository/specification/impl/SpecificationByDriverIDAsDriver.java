package by.bsuir.kuhalski.buber.repository.specification.impl;

import by.bsuir.kuhalski.buber.repository.specification.Specification;

import java.util.HashMap;
import java.util.Map;

public class SpecificationByDriverIDAsDriver implements Specification {

    private static final String SQL = " where driver=:driver_id";

    private Map<String, Object> parameterMap;

    public SpecificationByDriverIDAsDriver(long id) {
        this.parameterMap = new HashMap<>();
        parameterMap.put("driver_id", id);
    }

    @Override
    public String toSql() {
        return SQL;
    }

    @Override
    public Map<String, Object> getParameterMap() {
        return parameterMap;
    }
}
