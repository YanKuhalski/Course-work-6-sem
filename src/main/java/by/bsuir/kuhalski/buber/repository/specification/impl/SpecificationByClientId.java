package by.bsuir.kuhalski.buber.repository.specification.impl;

import by.bsuir.kuhalski.buber.repository.specification.Specification;

import java.util.HashMap;
import java.util.Map;

public class SpecificationByClientId implements Specification {

    private static final String SQL = " where client_id=:client_id";

    private Map<String, Object> parameterMap;

    public SpecificationByClientId(long id) {
        this.parameterMap = new HashMap<>();
        parameterMap.put("client_id", id);
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
