package supplier.application;

import java.util.Optional;
import supplier.domain.entities.Supplier;
import supplier.domain.entities.SupplierId;
import supplier.domain.entities.SupplierRepository;
import supplier.domain.usecases.DeleteSupplierUseCase;

public class DeleteSupplierUseCaseImpl implements DeleteSupplierUseCase {

    private final SupplierRepository supplierRepository;

    public DeleteSupplierUseCaseImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Optional<Supplier> deleteSupplierById(SupplierId id) {
        return supplierRepository.deleteById(id);
    }

}
