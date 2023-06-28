package user.application;

import shared.domain.valueobject.DomainError;


public class AuthenticateError extends DomainError {
    public AuthenticateError() {
        super("No hay usuario en la sesión");
    }
}
