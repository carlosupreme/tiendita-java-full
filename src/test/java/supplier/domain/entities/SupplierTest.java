package supplier.domain.entities;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SupplierTest {

    public SupplierTest() {
    }

    @Test
    public void shouldNotSetName() {
        assertThrows(InvalidSupplierName.class, () -> new SupplierName(""));
    }

    @Test
    public void shouldSetName() {
        assertDoesNotThrow(() -> new SupplierName("Valid name"));
    }

    @Test
    public void shouldNotSetAddress() {
        assertThrows(InvalidSupplierAddress.class, () -> new SupplierAddress("invalid address !#$%&/()=?¡¿'"));
    }

    @Test
    public void shouldSetAddress() {
        assertDoesNotThrow(() -> new SupplierAddress("Valid address #32"));
    }

    @Test
    public void shouldNotSetEmail() {
        assertThrows(InvalidSupplierEmail.class, () -> new SupplierEmail("invalid email"));
    }

    @Test
    public void shouldSetEmail() {
        assertDoesNotThrow(() -> new SupplierEmail("valid@email.com"));
    }

    @Test
    public void shouldNotSetPhone() {
        assertThrows(InvalidSupplierPhone.class, () -> new SupplierPhone("invalid"));
    }

    @Test
    public void shouldSetPhone() {
        assertDoesNotThrow(() -> new SupplierPhone("1234567890"));
    }

    @Test
    public void shouldSetAll() {
        SupplierName name = new SupplierName("valid");
        SupplierAddress address = new SupplierAddress("valid address #30");
        SupplierEmail email = new SupplierEmail("valid@email.co");
        SupplierPhone phoneNumber = new SupplierPhone("1324567980");
        Supplier supplier = new Supplier(name, address, email, phoneNumber);
        assertEquals("valid", supplier.name().value());
        assertEquals("valid address #30", supplier.address().value());
        assertEquals("valid@email.co", supplier.email().value());
        assertEquals("1324567980", supplier.phoneNumber().value());
    }
}
