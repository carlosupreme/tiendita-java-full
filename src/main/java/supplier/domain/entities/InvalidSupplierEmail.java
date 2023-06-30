package supplier.domain.entities;

import shared.domain.valueobject.InvalidArgument;

public class InvalidSupplierEmail extends InvalidArgument {

    public InvalidSupplierEmail(String msg) {
        super(msg);
    }

}
