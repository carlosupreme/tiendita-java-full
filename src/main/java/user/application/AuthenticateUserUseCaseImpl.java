package user.application;

import java.util.Optional;
import user.domain.entities.InvalidPassword;
import user.domain.entities.InvalidUsername;
import user.domain.entities.Session;
import user.domain.entities.User;
import user.domain.entities.UserRepository;
import user.domain.entities.Username;
import user.domain.usecases.AuthenticateUserUseCase;

public class AuthenticateUserUseCaseImpl implements AuthenticateUserUseCase {

    private UserRepository userRepository;

    public AuthenticateUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void authenticate(Username username, String password) {
        Optional<User> user = userRepository.search(username);

        ensureUserExists(user, username);
        ensurePasswordCorrect(user.get(), password);

        Session.getInstance().setUser(user.get());
    }

    private void ensureUserExists(Optional<User> user, Username username) {
        if (!user.isPresent()) {
            throw new InvalidUsername(username);
        }
    }

    private void ensurePasswordCorrect(User user, String password) {
        if (!user.passwordMatches(password)) {
            throw new InvalidPassword(user.username());
        }
    }
}
