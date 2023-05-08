package repositories;

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
public class ProveedorRepository implements CrudRepository<Proveedor> {

    private final Connection connection;

    public ProveedorRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Proveedor proveedor) throws SQLException, ValidationModelException {
//        PreparedStatement st = connection.prepareStatement("INSERT INTO proveedor (nombre, direccion, email, telefono) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
//        st.setString(1, proveedor.getNombre());
//        st.setString(2, proveedor.getDireccion());
//        st.setString(3, proveedor.getCorreoElectronico());
//        st.setInt(4, proveedor.getNumeroTelefonico());
//
//        if (st.executeUpdate() == 0) {
//            throw new SQLException("No se creó el proveedor.");
//        }
//
//        ResultSet generatedKeys = st.getGeneratedKeys();
//        if (generatedKeys.next()) {
//            proveedor.setId(generatedKeys.getInt(1));
//        } else {
//            throw new SQLException("No se obtuvo el ID");
//        }
//
//        System.out.println(proveedor);
    }

    @Override
    public List<Proveedor> findAll() throws SQLException, ValidationModelException {
        List<Proveedor> all = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM proveedores");

        while (rs.next()) {
            Proveedor proveedor = new Proveedor();
            proveedor.setId(rs.getInt("id"));
            proveedor.setNombre(rs.getString("nombre"));
            proveedor.setDireccion(rs.getString("direccion"));
            proveedor.setEmail(rs.getString("email"));
            proveedor.setTelefono(rs.getInt("telefono"));

            all.add(proveedor);
        }

        return all;
    }

    @Override
    public Proveedor findById(int id) throws SQLException, ValidationModelException {
        PreparedStatement st = connection.prepareStatement("SELECT * FROM proveedores WHERE id = ?");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();

        if (!rs.next()) {
            return null;
        }
        Proveedor proveedor = new Proveedor();
        proveedor.setId(rs.getInt("id"));
        proveedor.setNombre(rs.getString("nombre"));

        return proveedor;
    }

    @Override
    public void update(int id, Proveedor proveedor) throws SQLException {
        PreparedStatement st = connection.prepareStatement("UPDATE producto SET nombre = ?, descripcion = ?, precio = ?, id_proveedor = ? WHERE id = ?");
        st.setString(1, proveedor.getNombre());
        st.setString(2, proveedor.getDireccion());
        st.setString(3, proveedor.getEmail());
        st.setInt(4, proveedor.getTelefono());
        st.setInt(5, id);

        st.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement("DELETE FROM proveedor WHERE id = ? LIMIT 1");
        st.setInt(1, id);
        st.executeUpdate();
    }
}
