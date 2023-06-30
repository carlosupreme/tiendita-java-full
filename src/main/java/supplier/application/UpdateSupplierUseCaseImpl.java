package supplier.application;

import supplier.domain.entities.Supplier;
import supplier.domain.entities.SupplierId;
import supplier.domain.entities.SupplierRepository;
import supplier.domain.usecases.UpdateSupplierUseCase;

public class UpdateSupplierUseCaseImpl implements UpdateSupplierUseCase {

    private final SupplierRepository supplierRepository;

    public UpdateSupplierUseCaseImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void updateSupplier(SupplierId id, Supplier supplier) {
        supplierRepository.update(id, supplier);
    }

}
