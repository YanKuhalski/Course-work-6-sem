package by.bsuir.kuhalski.buber.repository.specification.impl;

import by.bsuir.kuhalski.buber.repository.specification.Specification;

import java.util.HashMap;
import java.util.Map;

public class SpecificationByLogin implements Specification {

    private static final String SQL = " where login=:login";

    private Map<String, Object> parameterMap;

    public SpecificationByLogin(String username) {
        this.parameterMap = new HashMap<>();
        parameterMap.put("login", username);
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
