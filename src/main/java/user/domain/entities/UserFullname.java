package user.domain.entities;

import shared.domain.valueobject.StringValueObject;

public class UserFullname extends StringValueObject {

    private static final int MIN_LENGTH = 3;
    private static final String USERFULLNAME_REGEX = "^(?!\\s*$)(?!.*[^a-zñáéíóúA-ZÑÁÉÍÓÚ \\s]).{" + MIN_LENGTH + ",}$";

    public UserFullname(String value) {
        super(value);
        validate(value);
    }

    private void validate(String value) {
        if (null == value || value.trim().isEmpty()) {
            throw new InvalidUserFullname("El nombre completo es requerido");
        }

        if (value.length() < MIN_LENGTH) {
            throw new InvalidUserFullname("El nombre completo debe contener al menos " + MIN_LENGTH + " caracteres");
        }

        if (!value.matches(USERFULLNAME_REGEX)) {
            throw new InvalidUserFullname("El nombre solo puede tener letras y espacios en blanco");
        }
    }

}
