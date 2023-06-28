package user.application;

import java.util.Optional;
import user.domain.entities.Session;
import user.domain.entities.User;
import user.domain.entities.UserRepository;
import user.domain.usecases.AuthenticateUserUseCase;

public class AuthenticateUserUseCaseImpl implements AuthenticateUserUseCase {

    private final UserRepository userRepository;

    public AuthenticateUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void authenticate(String username, String password) {
        Optional<User> user = userRepository.search(username);

        ensureUserExists(user, username);
        ensureValidCretentials(user.get(), password);

        Session.getInstance().setUser(user.get());
    }

    private void ensureUserExists(Optional<User> user, String username) {
        if (!user.isPresent()) {
            throw new UserNotExist(username);
        }
    }

    private void ensureValidCretentials(User user, String password) {
        if (!user.passwordMatches(password)) {
            throw new InvalidCredentials(user.username());
        }
    }
}
