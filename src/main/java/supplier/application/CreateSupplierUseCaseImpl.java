package supplier.application;

import supplier.domain.entities.Supplier;
import supplier.domain.entities.SupplierRepository;
import supplier.domain.usecases.CreateSupplierUseCase;

public class CreateSupplierUseCaseImpl implements CreateSupplierUseCase {
    
    private final SupplierRepository supplierRepository;
    
    public CreateSupplierUseCaseImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }
    
    @Override
    public void createSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }
    
}
