package shared.domain.valueobject.criteria;

import java.util.Objects;

public class Criteria {

    private final Filters filters;
    private final Order order;
    private final Integer limit;
    private final Integer offset;

    public Criteria(Filters filters, Order order, Integer limit, Integer offset) {
        this.filters = filters;
        this.order = order;
        this.limit = limit;
        this.offset = offset;
    }

    public static Criteria none() {
        return new Criteria(Filters.none(), Order.none(), null, null);
    }

    public boolean hasFilters() {
        return !filters.filters().isEmpty();
    }

    public boolean hasOrder() {
        return order.hasOrder();
    }

    public boolean hasLimit() {
        return limit != null;
    }

    public boolean hasOffset() {
        return offset != null;
    }


    public Filters filters() {
        return filters;
    }

    public Order order() {
        return order;
    }

    public Integer limit() {
        return limit;
    }

    public Integer offset() {
        return offset;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.filters);
        hash = 67 * hash + Objects.hashCode(this.order);
        hash = 67 * hash + Objects.hashCode(this.limit);
        hash = 67 * hash + Objects.hashCode(this.offset);
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
        final Criteria other = (Criteria) obj;
        if (!Objects.equals(this.filters, other.filters)) {
            return false;
        }
        if (!Objects.equals(this.order.orderBy(), other.order.orderBy())) {
            return false;
        }
        if (!Objects.equals(this.order.orderType(), other.order.orderType())) {
            return false;
        }
        if (!Objects.equals(this.limit, other.limit)) {
            return false;
        }
        return Objects.equals(this.offset, other.offset);
    }

    @Override
    public String toString() {
        return "Criteria{" + "filters=" + filters + ", order=" + order + ", limit=" + limit + ", offset=" + offset + '}';
    }
}
