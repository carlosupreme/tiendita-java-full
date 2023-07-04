package persistance.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import shared.domain.valueobject.criteria.Criteria;
import supplier.domain.entities.Supplier;
import supplier.domain.entities.SupplierAddress;
import supplier.domain.entities.SupplierEmail;
import supplier.domain.entities.SupplierId;
import supplier.domain.entities.SupplierName;
import supplier.domain.entities.SupplierPhone;
import supplier.domain.entities.SupplierRepository;

public final class SupplierInMemoryRepository implements SupplierRepository {

    private static HashMap<SupplierId, Supplier> data = new HashMap<>();
    private long currentId = 1L;

    public SupplierInMemoryRepository() {
        fillDummyData(5);
    }

    private Supplier copy(Supplier original) {
        return new Supplier(
                original.name(),
                original.address(),
                original.email(),
                original.phoneNumber()
        );
    }

    @Override
    public Optional<SupplierId> save(Supplier supplier) {
        SupplierId id = new SupplierId(currentId);
        Supplier supplierCopy = copy(supplier);
        supplierCopy.setId(id);
        data.put(id, supplierCopy);
        currentId += 1;

        System.out.println("Supplier created: \n" + data);

        return Optional.of(id);
    }

    @Override
    public Optional<Supplier> findById(SupplierId id) {

        System.out.println("Searching in data: \n" + data);

        if (!data.containsKey(id)) {
            return Optional.empty();
        }

        return Optional.of(data.get(id));

    }

    @Override
    public void update(SupplierId id, Supplier supplier) {

        if (data.containsKey(id)) {
            System.out.println("Before update: " + data.get(id));
            data.replace(id, supplier);
            System.out.println("After update: " + supplier);
        }

    }

    @Override
    public List<Supplier> matching(Criteria criteria) {
        return new ArrayList<>(data.values());
    }

    @Override
    public Optional<Supplier> deleteById(SupplierId id) {
        if (data.containsKey(id)) {
            System.out.println("List before delete " + data);
            Supplier supplier = data.get(id);
            data.remove(id);

            System.out.println("List after delete " + data);
            return Optional.of(supplier);
        }

        return Optional.empty();
    }

    public void fillDummyData(int count) {
        for (int i = 0; i < count; i++) {
            save(randomSupplier());
        }
    }

    private Supplier randomSupplier() {
        return new Supplier(
                new SupplierName(randomString(10)),
                new SupplierAddress(randomString(20)),
                new SupplierEmail(randomString(6) + "@" + randomString(5) + ".com"),
                new SupplierPhone(randomNumber())
        );
    }

    private String randomString(int length) {
        char[] letters = "wertyuiopasdfghjklzxcvbnmQWERTYUOÑLKJHGFDSAZXCVBNM".toCharArray();
        StringBuilder out = new StringBuilder();
        for (int i = 1; i <= length; i++) {
            int randIndex = (int) Math.floor(Math.random() * (10));
            char n = letters[randIndex];
            out.append(n);
        }
        return out.toString();
    }

    private String randomNumber() {
        Character[] numbers = new Character[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuilder number = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            int randIndex = (int) Math.floor(Math.random() * (10));
            char n = numbers[randIndex];
            number.append(n);
        }

        return number.toString();
    }

}
