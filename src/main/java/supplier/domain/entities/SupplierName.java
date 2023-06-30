package supplier.domain.entities;

import shared.domain.valueobject.StringValueObject;

public class SupplierName extends StringValueObject {

    private static final int MAX_LENGTH = 255;
    private static final String NAME_REGEX = "^(?!\\s*$)(?!.*[^a-zñáéíóúA-ZÑÁÉÍÓÚ0-9 \\s]).{1," + MAX_LENGTH + "}$";

    public SupplierName(String value) {
        super(value);
        validate(value);
    }

    public static void validate(String value) {
        if (null == value || value.trim().isEmpty()) {
            throw new InvalidSupplierName("El nombre es requerido");
        }

        if (value.length() > MAX_LENGTH) {
            throw new InvalidSupplierName("El nombre no debe tener más de " + MAX_LENGTH + " caracteres");
        }

        if (!value.matches(NAME_REGEX)) {
            throw new InvalidSupplierName("El nombre no debe contener caracteres especiales");
        }
    }

}
