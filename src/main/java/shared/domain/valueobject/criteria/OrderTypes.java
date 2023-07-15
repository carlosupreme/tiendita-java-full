package shared.domain.valueobject.criteria;

public enum OrderTypes {
    ASC("ASC"),
    DESC("DESC"),
    NONE("none");

    private final String value;

    OrderTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
