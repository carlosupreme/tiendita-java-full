package supplier.domain.entities;

public class Supplier {

    private SupplierId id;
    private SupplierName name;
    private SupplierAddress address;
    private SupplierEmail email;
    private SupplierPhone phoneNumber;

    public Supplier(SupplierName name, SupplierAddress address, SupplierEmail email, SupplierPhone phoneNumber) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Supplier(SupplierId id, SupplierName name, SupplierAddress address, SupplierEmail email, SupplierPhone phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public SupplierId id() {
        return id;
    }

    public void setId(SupplierId id) {
        this.id = id;
    }

    public SupplierName name() {
        return name;
    }

    public void setName(SupplierName name) {
        this.name = name;
    }

    public SupplierAddress address() {
        return address;
    }

    public void setAddress(SupplierAddress address) {
        this.address = address;
    }

    public SupplierEmail email() {
        return email;
    }

    public void setEmail(SupplierEmail email) {
        this.email = email;
    }

    public SupplierPhone phoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(SupplierPhone phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
