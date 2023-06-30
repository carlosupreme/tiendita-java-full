package supplier.domain.entities;

import shared.domain.valueobject.StringValueObject;

public class SupplierPhone extends StringValueObject {

    public SupplierPhone(String value) {
        super(value);
        validate(value);
    }

    public static void validate(String value) {
        if (null == value) {
            return;
        }

        if (value.length() != 10) {
            throw new InvalidSupplierPhone("El telefono debe ser de 10 dígitos");
        }

        if (!value.matches("\\d+")) {
            throw new InvalidSupplierPhone("El teléfono solo debe contener números");
        }
    }
}
