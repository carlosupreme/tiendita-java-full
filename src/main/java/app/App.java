package app;

import user.application.AuthenticateUserUseCaseImpl;
import user.application.RegisterUserUseCaseImpl;
import user.application.UserService;
import user.domain.entities.Session;
import user.domain.entities.UserRepository;
import user.domain.usecases.AuthenticateUserUseCase;
import user.domain.usecases.RegisterUserUseCase;
import user.infrastructure.MySQLUserRepository;

public class App {

    private static UserService userService;
    private static UserRepository userRepository;
    private static AuthenticateUserUseCase authenticateUserUseCase;
    private static RegisterUserUseCase registerUserUseCase;
    private static Session session;

    public static UserService userService() {
        if (null == userService) {
            userService = new UserService(authenticateUserUseCase(), registerUserUseCase());
        }

        return userService;
    }

    private static UserRepository userRepository() {
        if (null == userRepository) {
            userRepository = new MySQLUserRepository();
        }

        return userRepository;
    }

    private static AuthenticateUserUseCase authenticateUserUseCase() {
        if (null == authenticateUserUseCase) {
            authenticateUserUseCase = new AuthenticateUserUseCaseImpl(userRepository());
        }

        return authenticateUserUseCase;
    }

    private static RegisterUserUseCase registerUserUseCase() {
        if (null == registerUserUseCase) {
            registerUserUseCase = new RegisterUserUseCaseImpl(userRepository());
        }

        return registerUserUseCase;
    }

    public static Session session() {
        if (null == session) {
            session = Session.getInstance();
        }
        return session;
    }
}
