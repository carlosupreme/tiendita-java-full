package user.domain.usecases;

public interface AuthenticateUserUseCase {

    public void authenticate(String username, String password);
}
