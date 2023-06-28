package shared.domain.valueobject;

@SuppressWarnings("serial")
public class InvalidArgument extends DomainError {

    public InvalidArgument(String errorMessage) {
        super(errorMessage);
    }
}
