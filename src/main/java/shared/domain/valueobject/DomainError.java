package shared.domain.valueobject;

@SuppressWarnings("serial")
public class DomainError extends RuntimeException {

    public DomainError(String errorMessage) {
        super(errorMessage);
    }
}