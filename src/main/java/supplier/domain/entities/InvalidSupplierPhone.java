package supplier.domain.entities;

import shared.domain.valueobject.InvalidArgument;

public class InvalidSupplierPhone extends InvalidArgument {

    public InvalidSupplierPhone(String msg) {
        super(msg);
    }

}
