package supplier.application;

import java.util.Optional;
import supplier.domain.entities.Supplier;
import supplier.domain.entities.SupplierId;
import supplier.domain.entities.SupplierRepository;
import supplier.domain.usecases.RetrieveSupplierUseCase;

public class RetrieveSupplierUseCaseImpl implements RetrieveSupplierUseCase {

    private final SupplierRepository supplierRepository;

    public RetrieveSupplierUseCaseImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Optional<Supplier> findById(SupplierId id) {
        return supplierRepository.findById(id);
    }

}
