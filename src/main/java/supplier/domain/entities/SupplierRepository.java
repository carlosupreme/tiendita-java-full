package supplier.domain.entities;

import java.util.List;
import java.util.Optional;
import shared.domain.valueobject.criteria.Criteria;

public interface SupplierRepository {

    public Optional<SupplierId> save(Supplier supplier);

    public Optional<Supplier> findById(SupplierId id);

    public void update(SupplierId id, Supplier supplier);

    public List<Supplier> matching(Criteria criteria);

    public Optional<Supplier> deleteById(SupplierId id);

}
