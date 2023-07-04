package supplier.application;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import persistance.mock.SupplierInMemoryRepository;
import shared.domain.valueobject.criteria.Criteria;
import supplier.domain.entities.Supplier;
import supplier.domain.entities.SupplierAddress;
import supplier.domain.entities.SupplierEmail;
import supplier.domain.entities.SupplierName;
import supplier.domain.entities.SupplierPhone;
import supplier.domain.entities.SupplierRepository;
import supplier.domain.usecases.CreateSupplierUseCase;
import supplier.domain.usecases.DeleteSupplierUseCase;
import supplier.domain.usecases.GetSuppliersUseCase;
import supplier.domain.usecases.RetrieveSupplierUseCase;
import supplier.domain.usecases.UpdateSupplierUseCase;

public class SupplierServiceTest {

    private static SupplierService service;

    public SupplierServiceTest() {
        SupplierRepository repository = new SupplierInMemoryRepository();
        CreateSupplierUseCase createUseCase = new CreateSupplierUseCaseImpl(repository);
        DeleteSupplierUseCase deleteUseCase = new DeleteSupplierUseCaseImpl(repository);
        RetrieveSupplierUseCase retrieveUseCase = new RetrieveSupplierUseCaseImpl(repository);
        GetSuppliersUseCase getSuppliersUseCase = new GetSuppliersUseCaseImpl(repository);
        UpdateSupplierUseCase updateUseCase = new UpdateSupplierUseCaseImpl(repository);
        service = new SupplierService(createUseCase, deleteUseCase, retrieveUseCase, getSuppliersUseCase, updateUseCase);
    }

    @Test
    @Order(1)
    public void testSupplierService() {

        Supplier supplier = new Supplier(
                new SupplierName("carlos"),
                new SupplierAddress("Address #122"),
                new SupplierEmail("carlos@supplier.com"),
                new SupplierPhone("9512010584")
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
        List<Supplier> list = service.getSuppliers(new Criteria());
        System.out.println(list);

        System.out.println("Deleting supplier: " + supplier);
        service.deleteSupplierById(supplier.id());

    }

}
