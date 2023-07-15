package shared.domain.valueobject.criteria;

import java.util.Map;
import java.util.Objects;
import shared.domain.valueobject.InvalidArgument;

public class Filter {

    private final FilterField field;
    private final FilterOperator operator;
    private final FilterValue value;

    public Filter(FilterField field, FilterOperator operator, FilterValue value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    public static Filter fromValues(Map<String, String> values) {

        String field = values.get("field");
        String operator = values.get("operator");
        String value = values.get("value");

        if (field.isBlank() || operator.isBlank() || value.isBlank()) {
            throw new InvalidArgument("Filtro inválido");
        }

        return new Filter(
                new FilterField(field),
                FilterOperator.fromValue(operator),
                new FilterValue(value)
        );
    }

    public String field() {
        return field.value();
    }

    public String operator() {
        return operator.operator();
    }

    public String value() {
        return value.value();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.field);
        hash = 61 * hash + Objects.hashCode(this.operator);
        hash = 61 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Filter other = (Filter) obj;
        if (!Objects.equals(this.field.value(), other.field.value())) {
            return false;
        }
        if (!Objects.equals(this.operator.operator(), other.operator.operator())) {
            return false;
        }
        return Objects.equals(this.value.value(), other.value.value());
    }

    @Override
    public String toString() {
        return "Filter{" + "field=" + field.value() + ", operator=" + operator.operator() + ", value=" + value.value() + '}';
    }

}
