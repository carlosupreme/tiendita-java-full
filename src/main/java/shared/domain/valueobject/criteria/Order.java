package shared.domain.valueobject.criteria;

public class Order {

    private final OrderBy orderBy;
    private final OrderType orderType;

    public Order(OrderBy orderBy, OrderType orderType) {
        this.orderBy = orderBy;
        this.orderType = orderType;
    }

    public static Order fromValues(String orderBy, String orderType) {
        if (null == orderBy) {
            return Order.none();
        }

        return new Order(new OrderBy(orderBy), OrderType.fromValue(orderType != null ? orderType : OrderTypes.ASC.getValue()));
    }

    public static Order none() {
        return new Order(new OrderBy(""), new OrderType(OrderTypes.NONE));
    }

    public static Order desc(String orderBy) {
        return new Order(new OrderBy(orderBy), new OrderType(OrderTypes.DESC));
    }

    public static Order asc(String orderBy) {
        return new Order(new OrderBy(orderBy), new OrderType(OrderTypes.ASC));
    }

    public boolean hasOrder() {
        return !orderType.isNone();
    }

    public String orderBy() {
        return orderBy.value();
    }

    public String orderType() {
        return orderType.type();
    }

    @Override
    public String toString() {
        return "Order{" + "orderBy=" + orderBy.value() + ", orderType=" + orderType.type() + '}';
    }

}
