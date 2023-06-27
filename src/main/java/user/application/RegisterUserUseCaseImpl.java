package user.application;

import user.domain.entities.User;
import user.domain.entities.UserRepository;
import user.domain.usecases.RegisterUseCase;


public class RegisterUserUseCaseImpl implements RegisterUseCase {

    private final UserRepository userRepository;

    public RegisterUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        userRepository.save(user);
    }

}
