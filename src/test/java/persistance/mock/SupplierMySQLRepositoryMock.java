package persistance.mock;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import shared.domain.infrastructure.MySQLConnectionMock;
import shared.domain.valueobject.DomainError;
import shared.domain.valueobject.criteria.Criteria;
import shared.domain.valueobject.criteria.Operator;
import shared.domain.valueobject.criteria.Order;
import supplier.domain.SupplierNotExist;
import supplier.domain.entities.*;

public class SupplierMySQLRepositoryMock implements SupplierRepository {

    private final Connection connection = MySQLConnectionMock.getInstance().getConnection();

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

            SupplierId id = new SupplierId(generatedKeys.getLong(1));

            supplier.setId(id);
            return Optional.of(id);

        } catch (Exception e) {
            e.printStackTrace();
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

            return Optional.of(new Supplier(id, name, address, email, phone));

        } catch (SQLException | SupplierNotExist e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void update(SupplierId id, Supplier supplier) {
        try {
            PreparedStatement st = connection.prepareStatement("UPDATE proveedores SET nombre = ?, direccion = ?, email = ?,  telefono = ?  WHERE id = ?");
            st.setString(1, supplier.name().value());
            st.setString(2, supplier.address().value());
            st.setString(3, supplier.email().value());
            st.setString(4, supplier.phoneNumber().value());
            st.setLong(5, id.value());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DomainError("NO se actualizo el proveedor");
        }
    }

    @Override
    public List<Supplier> matching(Criteria criteria) {
        List<Supplier> suppliers = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            StringBuilder sql = new StringBuilder("SELECT * FROM proveedores WHERE activo = 1");

            if (criteria.hasFilters()) {
                criteria.filters().filters().forEach(filter -> {
                    sql.append(" AND ").append(filter.field()).append(" ");

                    if (filter.operator().equals(Operator.LIKE.getValue())) {
                        sql.append("LIKE '%").append(filter.value()).append("%'");
                    } else {
                        sql.append(filter.operator()).append(" '").append(filter.value()).append("'");
                    }
                });
            }

            if (criteria.hasOrder()) {
                Order order = criteria.order();
                sql.append(" ORDER BY ").append(order.orderBy()).append(" ").append(order.orderType());
            }

            if (criteria.hasLimit()) {
                sql.append(" LIMIT ").append(criteria.limit());
            }

            if (criteria.hasOffset()) {
                sql.append(" OFFSET ").append(criteria.offset());
            }

            System.out.println(sql);

            ResultSet rs = stmt.executeQuery(sql.toString());

            while (rs.next()) {
                suppliers.add(
                        new Supplier(
                                new SupplierId(rs.getLong("id")),
                                new SupplierName(rs.getString("nombre")),
                                new SupplierAddress(rs.getString("direccion")),
                                new SupplierEmail(rs.getString("email")),
                                new SupplierPhone(rs.getString("telefono"))
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    @Override
    public Optional<Supplier> deleteById(SupplierId id) {
        try {

            Optional<Supplier> supplier = this.findById(id);

            if (supplier.isEmpty()) {
                throw new Exception("No se encontró el proveedor");
            }

            PreparedStatement st = connection.prepareStatement("UPDATE proveedores SET activo = 0 WHERE id = ? LIMIT 1");
            st.setLong(1, id.value());
            st.executeUpdate();

            return supplier;

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
