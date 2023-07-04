package supplier.domain.entities;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Supplier{" + "id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", phoneNumber=" + phoneNumber + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Supplier other = (Supplier) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.phoneNumber, other.phoneNumber);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.address);
        hash = 59 * hash + Objects.hashCode(this.email);
        hash = 59 * hash + Objects.hashCode(this.phoneNumber);
        return hash;
    }

}
