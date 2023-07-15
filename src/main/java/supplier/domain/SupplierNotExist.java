package supplier.domain;

import shared.domain.valueobject.DomainError;
import supplier.domain.entities.SupplierId;

public class SupplierNotExist extends DomainError {
    public SupplierNotExist(SupplierId id) {
        super(String.format("El proveedor con id=%s no existe", id.toString()));
    }
}
