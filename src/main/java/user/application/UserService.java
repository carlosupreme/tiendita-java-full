package user.application;

import user.domain.entities.User;
import user.domain.usecases.AuthenticateUserUseCase;
import user.domain.usecases.RegisterUserUseCase;

public class UserService implements AuthenticateUserUseCase, RegisterUserUseCase {

    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final RegisterUserUseCase registerUseCase;

    public UserService(AuthenticateUserUseCase authenticateUserUseCase, RegisterUserUseCase registerUseCase) {
        this.authenticateUserUseCase = authenticateUserUseCase;
        this.registerUseCase = registerUseCase;
    }

    @Override
    public void authenticate(String username, String password) {
        authenticateUserUseCase.authenticate(username, password);
    }

    @Override
    public void registerUser(User user) {
        registerUseCase.registerUser(user);
    }

}
