package supplier.domain.entities;

import shared.domain.valueobject.StringValueObject;

public class SupplierEmail extends StringValueObject {

    private static final int MAX_LENGTH = 255;    
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2," + MAX_LENGTH + "}$";

    public SupplierEmail(String value) {
        super(value);
        validate(value);
    }

    public static void validate(String value) {
        if (null == value) {
            return;
        }

        if (value.length() > MAX_LENGTH) {
            throw new InvalidSupplierEmail("El email no debe tener más de " + MAX_LENGTH + " caracteres");
        }

        if (!value.matches(EMAIL_REGEX)) {
            throw new InvalidSupplierEmail("El email no es válido");
        }
    }

}
