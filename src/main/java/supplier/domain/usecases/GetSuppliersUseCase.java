package supplier.domain.usecases;

import java.util.List;
import shared.domain.valueobject.criteria.Criteria;
import supplier.domain.entities.Supplier;

public interface GetSuppliersUseCase {

    public List<Supplier> getSuppliers(Criteria criteria);
}
