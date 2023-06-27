package user.application;

import user.domain.entities.User;
import user.domain.entities.Username;
import user.domain.usecases.AuthenticateUserUseCase;
import user.domain.usecases.RegisterUseCase;

public class UserService implements AuthenticateUserUseCase, RegisterUseCase {

    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final RegisterUseCase registerUseCase;

    public UserService(AuthenticateUserUseCase authenticateUserUseCase, RegisterUseCase registerUseCase) {
        this.authenticateUserUseCase = authenticateUserUseCase;
        this.registerUseCase = registerUseCase;
    }

    @Override
    public void authenticate(Username username, String password) {
        authenticateUserUseCase.authenticate(username, password);
    }

    @Override
    public void registerUser(User user) {
        registerUseCase.registerUser(user);
    }

}
