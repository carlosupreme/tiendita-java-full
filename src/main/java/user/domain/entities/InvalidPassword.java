package user.domain.entities;

import shared.domain.valueobject.DomainError;

public class InvalidPassword extends DomainError {

    public InvalidPassword(Username username) {
        super(String.format("Las credenciales para <%s> son invalidas", username.value()));
    }

}
