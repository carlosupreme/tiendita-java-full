package user.domain.entities;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UserTest {

    public UserTest() {
    }

    @Test
    public void shouldSetAll() {
        UserId id = new UserId(100L);
        Password password = Password.hash("$Password123");
        Username username = new Username("carlos");
        UserFullname fullname = new UserFullname("Carlos Sosa");
        User user = new User(id, username, password, fullname);
        assertEquals(100L, user.id().value());
        assertEquals("carlos", user.username().value());
        assertEquals("Carlos Sosa", user.fullname().value());
    }

    @Test
    public void shouldMatchThePassword() {
        String plainPassword = "$Password123";
        Password password = Password.hash(plainPassword);
        Username username = new Username("carlos");
        UserFullname fullname = new UserFullname("Carlos Sosa");
        User user = new User(username, password, fullname);
        System.out.println("contrasña: " + plainPassword);
        System.out.println("contrasña hasheada: " + password);
        assertTrue(user.passwordMatches(plainPassword));
    }

    @Test
    public void shouldNotSetPassword() {
        String plainPassword = "PasswordValid00";
        assertThrows(InvalidPassword.class, () -> {
            Password password = new Password(plainPassword);
        });
    }

}
