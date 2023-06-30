package supplier.application;

import java.util.List;
import shared.domain.valueobject.criteria.Criteria;
import supplier.domain.entities.Supplier;
import supplier.domain.entities.SupplierRepository;
import supplier.domain.usecases.GetSuppliersUseCase;

public class GetSuppliersUseCaseImpl implements GetSuppliersUseCase {

    private final SupplierRepository supplierRepository;

    public GetSuppliersUseCaseImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> getSuppliers(Criteria criteria) {
        return supplierRepository.matching(criteria);
    }

}
