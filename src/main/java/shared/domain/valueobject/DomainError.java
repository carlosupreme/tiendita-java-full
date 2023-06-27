package shared.domain.valueobject;

public class DomainError extends RuntimeException {

    public DomainError(String errorMessage) {
        super(errorMessage);
    }
}
