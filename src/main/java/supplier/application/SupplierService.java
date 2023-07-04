package supplier.application;

import java.util.List;
import java.util.Optional;
import shared.domain.valueobject.criteria.Criteria;
import supplier.domain.entities.Supplier;
import supplier.domain.entities.SupplierId;
import supplier.domain.usecases.CreateSupplierUseCase;
import supplier.domain.usecases.DeleteSupplierUseCase;
import supplier.domain.usecases.GetSuppliersUseCase;
import supplier.domain.usecases.RetrieveSupplierUseCase;
import supplier.domain.usecases.UpdateSupplierUseCase;

public class SupplierService implements CreateSupplierUseCase, DeleteSupplierUseCase, RetrieveSupplierUseCase, GetSuppliersUseCase, UpdateSupplierUseCase {
    
    private final CreateSupplierUseCase createSupplierUseCase;
    private final DeleteSupplierUseCase deleteSupplierUseCase;
    private final RetrieveSupplierUseCase retrieveSupplierUseCase;
    private final GetSuppliersUseCase getSuppliersUseCase;
    private final UpdateSupplierUseCase updateSupplierUseCase;
    
    public SupplierService(CreateSupplierUseCase createSupplierUseCase, DeleteSupplierUseCase deleteSupplierUseCase, RetrieveSupplierUseCase retrieveSupplierUseCase, GetSuppliersUseCase getSuppliersUseCase, UpdateSupplierUseCase updateSupplierUseCase) {
        this.createSupplierUseCase = createSupplierUseCase;
        this.deleteSupplierUseCase = deleteSupplierUseCase;
        this.retrieveSupplierUseCase = retrieveSupplierUseCase;
        this.getSuppliersUseCase = getSuppliersUseCase;
        this.updateSupplierUseCase = updateSupplierUseCase;
    }
    
    @Override
    public void saveSupplier(Supplier supplier) {
        createSupplierUseCase.saveSupplier(supplier);
    }
    
    @Override
    public Optional<Supplier> deleteSupplierById(SupplierId id) {
        return deleteSupplierUseCase.deleteSupplierById(id);
    }
    
    @Override
    public Optional<Supplier> findById(SupplierId id) {
        return retrieveSupplierUseCase.findById(id);
    }
    
    @Override
    public List<Supplier> getSuppliers(Criteria criteria) {
        return getSuppliersUseCase.getSuppliers(criteria);
    }
    
    @Override
    public void updateSupplier(SupplierId id, Supplier supplier) {
        updateSupplierUseCase.updateSupplier(id, supplier);
    }
    
}
