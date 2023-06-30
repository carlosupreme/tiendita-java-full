package supplier.domain.entities;

import shared.domain.valueobject.StringValueObject;

public class SupplierAddress extends StringValueObject {

    private static final int MAX_LENGTH = 100;
    private static final String ADDRESS_REGEX = "^[\\p{L}0-9\\s#\\-'áéíóúÁÉÍÓÚ]+$";

    public SupplierAddress(String value) {
        super(value);
        validate(value);
    }

    public static void validate(String value) {
        if (null == value) {
            return;
        }

        if (value.length() > MAX_LENGTH) {
            throw new InvalidSupplierAddress("La dirección no debe tener más de " + MAX_LENGTH + " caracteres");
        }

        if (!value.matches(ADDRESS_REGEX)) {
            throw new InvalidSupplierAddress("La dirección no debe contener caracteres especiales, solo admite #");
        }
    }

}
