package by.bsuir.kuhalski.buber.repository.specification.impl;

import by.bsuir.kuhalski.buber.model.Role;
import by.bsuir.kuhalski.buber.repository.specification.Specification;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SpecificationByRoleId implements Specification {



    private static final String SQL = " join user_role on id = user_id where role_id = :role_id  ";

    private Map<String, Object> parameterMap;

    public SpecificationByRoleId(long role) {
        this.parameterMap = new HashMap<>();
        parameterMap.put("role_id", role);
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
