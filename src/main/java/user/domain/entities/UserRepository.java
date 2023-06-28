package user.domain.entities;

import java.util.Optional;

public interface UserRepository {

    public Optional<User> search(String username);

    public UserId save(User user);
}
