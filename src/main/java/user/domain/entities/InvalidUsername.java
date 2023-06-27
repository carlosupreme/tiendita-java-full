package user.domain.entities;

import shared.domain.valueobject.DomainError;

public class InvalidUsername extends DomainError {

    public InvalidUsername(Username username) {
        super(String.format("El nombre de usuario <%s> no existe", username.value()));
    }

}
