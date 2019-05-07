package by.bsuir.kuhalski.buber.repository.specification;

import java.util.Map;

public interface Specification {
    String toSql();

    Map<String, Object> getParameterMap();
}
