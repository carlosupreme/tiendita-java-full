package supplier.domain.usecases;

import supplier.domain.entities.Supplier;
import supplier.domain.entities.SupplierId;

public interface UpdateSupplierUseCase {

    public void updateSupplier(SupplierId id, Supplier supplier);
}
