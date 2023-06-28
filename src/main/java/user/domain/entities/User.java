package user.domain.entities;

public class User {

    private UserId id;
    private Username username;
    private Password password;
    private UserFullname fullname;

    public User(Username username, Password password, UserFullname fullname) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
    }

    public User(UserId id, Username username, Password password, UserFullname fullname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
    }

    public void setId(UserId id) {
        this.id = id;
    }

    public UserId id() {
        return id;
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    public Username username() {
        return username;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public Password password() {
        return password;
    }

    public UserFullname fullname() {
        return fullname;
    }

    public void setFullname(UserFullname fullname) {
        this.fullname = fullname;
    }

    public boolean passwordMatches(String password) {
        return this.password.passwordMatches(password);
    }

}
