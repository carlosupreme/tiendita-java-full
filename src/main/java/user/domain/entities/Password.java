package user.domain.entities;

import org.mindrot.jbcrypt.BCrypt;
import shared.domain.valueobject.StringValueObject;

public final class Password extends StringValueObject {

    private static final int MAX_LENGTH = 255;
    private static final int MIN_LENGTH = 8;
    private static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,255}$";

    public Password(String hashedPassword) {
        super(hashedPassword);
    }

    public static Password hash(String plainPassword) {
        validate(plainPassword);
        String hashed = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        return new Password(hashed);
    }

    public boolean passwordMatches(String plainPassword) {
        return BCrypt.checkpw(plainPassword, value());
    }

    public static void validate(String value) {
        if (null == value || value.isBlank() || "".equals(value)) {
            throw new InvalidPassword("La contraseña es requerida");
        }

        if (value.length() < MIN_LENGTH) {
            throw new InvalidPassword("La contraseña debe tener al menos " + MIN_LENGTH + " caracteres");
        }

        if (value.length() > MAX_LENGTH) {
            throw new InvalidPassword("La contraseña no debe tener más de " + MAX_LENGTH + " caracteres");
        }

        if (!value.matches(PASSWORD_REGEX)) {
            throw new InvalidPassword("La contraseña debe contener al menos 1 letra mayúscula, 1 letra minúscula y 1 número");
        }
    }

}
