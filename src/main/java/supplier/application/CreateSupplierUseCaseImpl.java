package supplier.application;

import java.util.Optional;
import shared.domain.valueobject.DomainError;
import supplier.domain.entities.Supplier;
import supplier.domain.entities.SupplierId;
import supplier.domain.entities.SupplierRepository;
import supplier.domain.usecases.CreateSupplierUseCase;

public class CreateSupplierUseCaseImpl implements CreateSupplierUseCase {

    private final SupplierRepository supplierRepository;

    public CreateSupplierUseCaseImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void saveSupplier(Supplier supplier) {
        Optional<SupplierId> id = supplierRepository.save(supplier);

        if (!id.isPresent()) {
            throw new DomainError("No se obtuvo el id del proveedor");
        }

        supplier.setId(id.get());
    }

}
