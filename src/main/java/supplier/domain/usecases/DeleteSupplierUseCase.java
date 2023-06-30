package supplier.domain.usecases;

import java.util.Optional;
import supplier.domain.entities.Supplier;
import supplier.domain.entities.SupplierId;

public interface DeleteSupplierUseCase {

    public Optional<Supplier> deleteSupplierById(SupplierId id);
}
