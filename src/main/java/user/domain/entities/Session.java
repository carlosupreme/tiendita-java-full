package user.domain.entities;

public class Session {

    private static Session instance;

    private Session() {
    }

    public static Session getInstance() {
        if (null == instance) {
            instance = new Session();
        }

        return instance;
    }

    private User user;

    public User getUser() {
        if (null == user) {
            throw new RuntimeException("Debe haber un usuario en la sesión");
        }

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
