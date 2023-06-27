package user.domain.entities;

import java.util.Optional;

public interface UserRepository {

    public Optional<User> search(Username username);

    public void save(User user);
}
