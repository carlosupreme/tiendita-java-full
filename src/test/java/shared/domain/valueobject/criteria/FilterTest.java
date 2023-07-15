package shared.domain.valueobject.criteria;

import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import shared.domain.valueobject.InvalidArgument;

public class FilterTest {

    public FilterTest() {
    }

    @Test
    public void testFromValues() {

        String value = "carlos";
        String field = "name";
        String operator = "=";
        HashMap<String, String> values = new HashMap<>();
        values.put("value", value);
        values.put("field", field);
        values.put("operator", operator);

        Filter expResult = new Filter(
                new FilterField(field),
                new FilterOperator(Operator.EQUAL),
                new FilterValue(value)
        );
        Filter result = Filter.fromValues(values);
        assertEquals(expResult, result);
    }

    @Test
    public void shouldThrowAnErrorByOperator() {
        String operator = "#"; // an invalid operator

        assertThrows(InvalidArgument.class, () -> {
            FilterOperator.fromValue(operator);
        });

    }   

}
