package supplier.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import persistance.mock.SupplierMySQLRepositoryMock;
import shared.domain.valueobject.criteria.Criteria;
import shared.domain.valueobject.criteria.Filter;
import shared.domain.valueobject.criteria.FilterField;
import shared.domain.valueobject.criteria.FilterOperator;
import shared.domain.valueobject.criteria.FilterValue;
import shared.domain.valueobject.criteria.Filters;
import shared.domain.valueobject.criteria.Operator;
import shared.domain.valueobject.criteria.Order;
import shared.domain.valueobject.criteria.OrderBy;
import shared.domain.valueobject.criteria.OrderType;
import shared.domain.valueobject.criteria.OrderTypes;
import supplier.domain.entities.*;
import supplier.domain.usecases.*;

public class SupplierServiceTest {

    private static SupplierService service;

    public SupplierServiceTest() {
        SupplierRepository repository = new SupplierMySQLRepositoryMock();
        CreateSupplierUseCase createUseCase = new CreateSupplierUseCaseImpl(repository);
        DeleteSupplierUseCase deleteUseCase = new DeleteSupplierUseCaseImpl(repository);
        RetrieveSupplierUseCase retrieveUseCase = new RetrieveSupplierUseCaseImpl(repository);
        GetSuppliersUseCase getSuppliersUseCase = new GetSuppliersUseCaseImpl(repository);
        UpdateSupplierUseCase updateUseCase = new UpdateSupplierUseCaseImpl(repository);
        service = new SupplierService(createUseCase, deleteUseCase, retrieveUseCase, getSuppliersUseCase, updateUseCase);
    }

    @Test
    public void testSupplierService() {

        List<Filter> filters = new ArrayList<>();

        filters.add(new Filter(
                new FilterField("nombre"),
                new FilterOperator(Operator.LIKE),
                new FilterValue("ca")
        ));

        filters.add(new Filter(
                new FilterField("id"),
                new FilterOperator(Operator.GT),
                new FilterValue("2")
        ));

        Criteria criteria = new Criteria(
                new Filters(filters),
                new Order(new OrderBy("id"), new OrderType(OrderTypes.DESC)),
                100, 0
        );

        Supplier supplier = new Supplier(
                new SupplierName("Carlos"),
                new SupplierAddress("Address"),
                new SupplierEmail("emailcarlos@email.com"),
                new SupplierPhone("9998887774")
        );

        System.out.println("Saving supplier");
        service.saveSupplier(supplier);

        System.out.println("findById: id = " + supplier.id());
        Optional<Supplier> expResult = Optional.of(supplier);
        Optional<Supplier> result = service.findById(supplier.id());
        assertEquals(expResult, result);

        System.out.println("Supplier Found:\n" + supplier + "\n" + result.get());

        System.out.println("Updating supplier");
        supplier.setEmail(new SupplierEmail("newEmail@supplier.com"));
        service.updateSupplier(supplier.id(), supplier);
        System.out.println("Searching all");
        List<Supplier> list = service.getSuppliers(criteria);
        System.out.println(list);

        System.out.println("Deleting supplier: " + supplier);
        service.deleteSupplierById(supplier.id());
    }

}
