package user.domain.entities;

import shared.domain.valueobject.StringValueObject;

public final class Username extends StringValueObject {

    private static final int MAX_LENGTH = 100;
    private static final int MIN_LENGTH = 6;
    private static final String USERNAME_REGEX = "^[a-zñA-ZÑ0-9_]{" + MIN_LENGTH + "," + MAX_LENGTH + "}$";

    public Username(String value) {
        super(value);
        validate(value);
    }

    public static void validate(String value) {

        if (null == value || value.trim().isEmpty()) {
            throw new InvalidUsername("El username es requerido");
        }

        if (value.length() < MIN_LENGTH) {
            throw new InvalidUsername("El username debe tener al menos " + MIN_LENGTH + " caracteres");
        }

        if (value.length() > MAX_LENGTH) {
            throw new InvalidUsername("El username no debe tener más de " + MAX_LENGTH + " caracteres");
        }

        if (!value.matches(USERNAME_REGEX)) {
            throw new InvalidUsername("El username solo puede tener letras sin acentos, números y guiones bajos");
        }

    }

}
