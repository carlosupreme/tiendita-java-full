package supplier.domain.usecases;

import java.util.Optional;
import supplier.domain.entities.Supplier;
import supplier.domain.entities.SupplierId;

public interface RetrieveSupplierUseCase {

    public Optional<Supplier> findById(SupplierId id);
}
