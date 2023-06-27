package user.domain.usecases;

import user.domain.entities.Username;

public interface AuthenticateUserUseCase {

    public void authenticate(Username username, String password);
}
