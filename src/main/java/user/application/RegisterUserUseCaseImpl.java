package user.application;

import user.domain.entities.User;
import user.domain.entities.UserId;
import user.domain.entities.UserRepository;
import user.domain.usecases.RegisterUserUseCase;

public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepository userRepository;

    public RegisterUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        UserId userId = userRepository.save(user);
        user.setId(userId);
    }

}
