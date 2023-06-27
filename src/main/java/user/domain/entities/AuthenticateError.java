package user.domain.entities;


public class AuthenticateError extends RuntimeException {
    public AuthenticateError() {
        super("No hay usuario en la sesión");
    }
}
