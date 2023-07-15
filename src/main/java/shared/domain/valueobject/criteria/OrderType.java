package shared.domain.valueobject.criteria;

import shared.domain.valueobject.InvalidArgument;

public class OrderType {

    private final OrderTypes type;

    public OrderType(OrderTypes type) {
        this.type = type;
    }

    public static OrderType fromValue(String value) {
        for (OrderTypes operatorValue : OrderTypes.values()) {
            if (value.equals(operatorValue.toString())) {
                return new OrderType(operatorValue);
            }
        }

        throw new InvalidArgument(String.format("El tipo <%s> es invalido", value));
    }

    public boolean isNone() {
        return this.type == OrderTypes.NONE;
    }

    public boolean isAsc() {
        return this.type == OrderTypes.ASC;
    }
    
    public String type(){
        return type.getValue();
    }

}
