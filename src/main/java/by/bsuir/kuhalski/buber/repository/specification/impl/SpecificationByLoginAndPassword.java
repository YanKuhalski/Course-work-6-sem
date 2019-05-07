package by.bsuir.kuhalski.buber.repository.specification.impl;

import by.bsuir.kuhalski.buber.repository.specification.Specification;

import java.util.HashMap;
import java.util.Map;

public class SpecificationByLoginAndPassword implements Specification {

    private static final String SQL = " where login=:login and password=:password";

    private Map<String, Object> parameterMap;

    public SpecificationByLoginAndPassword(String login, String password) {
        this.parameterMap = new HashMap<>();
        parameterMap.put("login", login);
        parameterMap.put("password", password);
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
