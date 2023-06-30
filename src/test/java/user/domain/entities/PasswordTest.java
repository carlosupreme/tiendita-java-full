package user.domain.entities;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PasswordTest {

    public PasswordTest() {
    }

    @Test
    public void shouldHashAPassword() {
        String plainPassword = "Password123$";
        Password result = Password.hash(plainPassword);
        assertNotSame(plainPassword, result);
    }

    @Test
    public void shouldMatchThePasswords() {
        String plainPassword = "Password123$";
        Password instance = Password.hash(plainPassword);
        boolean result = instance.passwordMatches(plainPassword);
        assertTrue(result);
    }

    @Test
    public void shouldThrowAnErrorByInvalidPassword() {
        assertThrows(InvalidPassword.class, () -> {
            String value = "";
            Password.validate(value);
        });
    }

    public void shouldMatchHashedPasswords() {
        Password[] attempts = new Password[]{
            Password.hash("Hola mundo 10"),
            Password.hash("ValidPassword_123"),
            Password.hash("tW4],IE;53(5p5]Fg(OioJwKaMdlwQB}pY1-%&9,-$dPw,RKU]")
        };

        for (Password password : attempts) {
            assertDoesNotThrow(() -> Password.ensureIsHashed(password.value()));
        }

    }

}
