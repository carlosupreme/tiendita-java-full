package supplier.domain.entities;

import shared.domain.valueobject.InvalidArgument;

public class InvalidSupplierAddress extends InvalidArgument {

    public InvalidSupplierAddress(String msg) {
        super(msg);
    }

}
