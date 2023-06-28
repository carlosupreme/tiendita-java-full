package user.application;

import shared.domain.valueobject.DomainError;

@SuppressWarnings("serial")
public class UserNotExist extends DomainError {

    public UserNotExist(String username) {
        super(String.format("El nombre de usuario <%s> no existe", username));
    }

}
