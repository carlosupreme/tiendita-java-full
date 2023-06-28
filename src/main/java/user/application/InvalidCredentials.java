package user.application;

import shared.domain.valueobject.DomainError;
import user.domain.entities.Username;


public class InvalidCredentials extends DomainError {
    public InvalidCredentials(Username username){
        super(String.format("Las credenciales para <%s> son invalidas", username.value()));
    }
}
