package shared.domain.valueobject.criteria;

import shared.domain.valueobject.InvalidArgument;

public class FilterOperator {

    public final Operator operator;

    public FilterOperator(Operator operator) {
        this.operator = operator;
    }

    public static FilterOperator fromValue(String value) {
        for (Operator operatorValue : Operator.values()) {
            if (value.equals(operatorValue.getValue())) {
                return new FilterOperator(operatorValue);
            }
        }

        throw new InvalidArgument(String.format("El operador <%s> es invalido", value));
    }

    public String operator() {
        return operator.getValue();
    }

}
