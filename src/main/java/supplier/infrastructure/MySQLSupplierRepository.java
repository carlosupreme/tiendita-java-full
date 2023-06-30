package supplier.infrastructure;

import app.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import shared.domain.valueobject.criteria.Criteria;
import supplier.domain.entities.Supplier;
import supplier.domain.entities.SupplierId;
import supplier.domain.entities.SupplierRepository;

public class MySQLSupplierRepository implements SupplierRepository {

    private final static Connection connection = MySQLConnection.getInstance().getConnection();

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
        throw new UnsupportedOperationException("Not supported yet.");
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
