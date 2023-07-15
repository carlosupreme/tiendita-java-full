package supplier.infrastructure;

import shared.domain.valueobject.criteria.Criteria;
import supplier.domain.SupplierNotExist;
import supplier.domain.entities.*;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class MySQLSupplierRepository implements SupplierRepository {

    private final Connection connection;

    public MySQLSupplierRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<SupplierId> save(Supplier supplier) {
        try {
            String sql = "INSERT INTO proveedores (nombre, direccion, email, telefono) VALUES (?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, supplier.name().value());
            st.setString(2, supplier.address().value());
            st.setString(3, supplier.email().value());
            st.setString(4, supplier.phoneNumber().value());

            if (st.executeUpdate() == 0) {
                throw new Exception("No se creó el proveedor.");
            }

            ResultSet generatedKeys = st.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new Exception("No se obtuvo el ID");
            }

            return Optional.of(new SupplierId(generatedKeys.getLong(1)));

        } catch (Exception e) {
            System.err.println(e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Supplier> findById(SupplierId id) {
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM proveedores WHERE id = ?");
            st.setLong(1, id.value());

            ResultSet rs = st.executeQuery();

            if (!rs.next()) {
                throw new SupplierNotExist(id);
            }

            SupplierName name = new SupplierName(rs.getString("nombre"));
            SupplierAddress address = new SupplierAddress(rs.getString("direccion"));
            SupplierEmail email = new SupplierEmail(rs.getString("email"));
            SupplierPhone phone = new SupplierPhone(rs.getString("telefono"));

            return Optional.of(new Supplier(name, address, email, phone));

        } catch (SQLException | SupplierNotExist e) {
            e.printStackTrace();
            return Optional.empty();
        }

    }

    @Override
    public void update(SupplierId id, Supplier supplier) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Supplier> matching(Criteria criteria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Supplier> deleteById(SupplierId id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
