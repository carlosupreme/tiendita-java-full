package user.domain.entities;

public class User {

    private final Username username;
    private final Password password;
    private final String name;

    public User(Username username, Password password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public Username username() {
        return username;
    }

    public String name() {
        return name;
    }

    public boolean passwordMatches(String password) {
        return Password.check(password, this.password.value());
    }

}
