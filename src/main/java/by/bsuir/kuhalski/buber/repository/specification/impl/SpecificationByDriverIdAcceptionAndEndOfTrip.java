package by.bsuir.kuhalski.buber.repository.specification.impl;

import by.bsuir.kuhalski.buber.repository.specification.Specification;

import java.util.HashMap;
import java.util.Map;

public class SpecificationByDriverIdAcceptionAndEndOfTrip implements Specification {

    private static final String SQL = " where driver_id=:driver_id and is_accepted=:is_accepted and is_finished=:is_finished";

    private Map<String, Object> parameterMap;

    @Override
    public String toSql() {
        return SQL;
    }

    @Override
    public Map<String, Object> getParameterMap() {
        return parameterMap;
    }

    public SpecificationByDriverIdAcceptionAndEndOfTrip(long carDriverId, boolean isAccepted, boolean isFinished) {
        this.parameterMap = new HashMap<>();
        parameterMap.put("driver_id", carDriverId);
        parameterMap.put("is_accepted", isAccepted);
        parameterMap.put("is_finished", isFinished);
    }
}
