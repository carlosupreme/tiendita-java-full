package shared.domain.valueobject.criteria;

public enum Operator {
    EQUAL("="),
    NOT_EQUAL("!="),
    GT(">"),
    LT("<"),
    CONTAINS("IN"),
    NOT_CONTAINS("NOT IN"),
    LIKE("LIKE");

    private final String value;

    Operator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
