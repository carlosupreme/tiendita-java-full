package supplier.domain.usecases;

import supplier.domain.entities.Supplier;

public interface CreateSupplierUseCase {

    public void saveSupplier(Supplier supplier);
}
