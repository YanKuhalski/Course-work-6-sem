package by.bsuir.kuhalski.buber.repository.specification.impl;

import by.bsuir.kuhalski.buber.repository.specification.Specification;

import java.util.HashMap;
import java.util.Map;

public class SpecificationByRole implements Specification {

    private static final String SQL = " where role=:role ";

    private Map<String, Object> parameterMap;

    public SpecificationByRole(String role) {
        this.parameterMap = new HashMap<>();
        parameterMap.put("role", role);
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
