package repositories;

import app.MySQLConnection;
import exceptions.ValidationModelException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Proveedor;

/**
 *
 * @author ili
 */
public class ProveedorRepository {

    private final Connection connection;

    public ProveedorRepository() {
        this.connection = MySQLConnection.getInstance().getConnection();
    }

    public void save(Proveedor proveedor) throws SQLException, ValidationModelException {
        PreparedStatement st = connection.prepareStatement("INSERT INTO proveedores (nombre, direccion, email, telefono) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        st.setString(1, proveedor.getNombre());
        st.setString(2, proveedor.getDireccion());
        st.setString(3, proveedor.getEmail());
        st.setString(4, proveedor.getTelefono());

        if (st.executeUpdate() == 0) {
            throw new SQLException("No se creó el proveedor.");
        }

        ResultSet generatedKeys = st.getGeneratedKeys();
        if (generatedKeys.next()) {
            proveedor.setId(generatedKeys.getInt(1));
        } else {
            throw new SQLException("No se obtuvo el ID");
        }

        System.out.println(proveedor);
    }

    public List<Proveedor> findAll(ProveedorCriteria criteria) throws SQLException, ValidationModelException {
        List<Proveedor> all = new ArrayList<>();
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM proveedores WHERE activo = 1";

        query += " AND nombre like '%" + criteria.nombre + "%'";
        query += " AND direccion like '%" + criteria.direccion + "%'";
        query += " AND email like '%" + criteria.email + "%'";
        query += " AND telefono like '%" + criteria.telefono + "%'";

        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            Proveedor proveedor = new Proveedor();
            proveedor.setId(rs.getLong("id"));
            proveedor.setNombre(rs.getString("nombre"));
            proveedor.setDireccion(rs.getString("direccion"));
            proveedor.setEmail(rs.getString("email"));
            proveedor.setTelefono(rs.getString("telefono"));

            all.add(proveedor);
        }

        return all;
    }

    public Proveedor findById(long id) throws SQLException, ValidationModelException {
        PreparedStatement st = connection.prepareStatement("SELECT * FROM proveedores WHERE id = ?");
        st.setLong(1, id);
        ResultSet rs = st.executeQuery();

        if (!rs.next()) {
            return null;
        }
        Proveedor proveedor = new Proveedor();
        proveedor.setId(rs.getLong("id"));
        proveedor.setNombre(rs.getString("nombre"));
        proveedor.setDireccion(rs.getString("direccion"));
        proveedor.setTelefono(rs.getString("telefono"));
        proveedor.setEmail(rs.getString("email"));

        return proveedor;
    }

    public void update(long id, Proveedor proveedor) throws SQLException {
        PreparedStatement st = connection.prepareStatement("UPDATE proveedores SET nombre = ?, direccion = ?, email = ?,  telefono = ?  WHERE id = ?");
        st.setString(1, proveedor.getNombre());
        st.setString(2, proveedor.getDireccion());
        st.setString(3, proveedor.getEmail());
        st.setString(4, proveedor.getTelefono());
        st.setLong(5, id);

        st.executeUpdate();
    }

    public void delete(long id) throws SQLException {
        PreparedStatement st = connection.prepareStatement("UPDATE proveedores SET activo = 0 WHERE id = ? LIMIT 1");
        st.setLong(1, id);
        st.executeUpdate();
    }
}
