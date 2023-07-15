package shared.domain.valueobject.criteria;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CriteriaTest {

    public CriteriaTest() {
    }

    @Test
    public void shouldCreateACriteriaInstance() {
        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter(new FilterField("name"), new FilterOperator(Operator.EQUAL), new FilterValue("carlos")));

        Criteria instance = new Criteria(new Filters(filters), Order.none(), null, null);

        System.out.println(instance);

        assertNotNull(instance);
    }

    @Test
    public void shouldCreateAnEmptyCriteria() {
        Criteria instance = Criteria.none();

        System.out.println(instance);
        assertNotNull(instance);
    }

}
