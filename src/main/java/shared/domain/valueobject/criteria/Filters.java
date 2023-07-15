package shared.domain.valueobject.criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Filters {

    private final List<Filter> filters;

    public Filters(List<Filter> filters) {
        this.filters = filters;
    }

    public static Filters fromValues(List<Map<String, String>> filters) {
        List<Filter> filterList = new ArrayList<>();

        filters.stream().map(Filter::fromValues).forEach(filterList::add);

        return new Filters(filterList);
    }

    public static Filters none() {
        List<Filter> filters = new ArrayList<>();
        return new Filters(filters);
    }

    public List<Filter> filters() {
        return filters;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.filters);
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
        final Filters other = (Filters) obj;
        return Objects.equals(this.filters, other.filters);
    }

    @Override
    public String toString() {
        return "Filters{" + "filters=" + filters + '}';
    }

}
