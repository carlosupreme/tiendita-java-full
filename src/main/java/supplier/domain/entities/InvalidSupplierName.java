package supplier.domain.entities;

import shared.domain.valueobject.InvalidArgument;

public class InvalidSupplierName extends InvalidArgument {

    public InvalidSupplierName(String msg) {
        super(msg);
    }

}
