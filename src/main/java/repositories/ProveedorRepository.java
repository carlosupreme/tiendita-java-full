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
        PreparedStatement st = connection.prepareStatement("INSERT INTO proveedor (nombre, direccion, correo_electronico, numero_telefonico) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        st.setString(1, proveedor.getNombre());
        st.setString(2, proveedor.getDireccion());
        st.setString(3, proveedor.getCorreoElectronico());
        st.setInt(4, proveedor.getNumeroTelefonico());

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

    @Override
    public List<Proveedor> findAll() throws SQLException, ValidationModelException {
        List<Proveedor> all = new ArrayList<>();

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM proveedor");        

        while (rs.next()) {
            Proveedor proveedor = new Proveedor();
            proveedor.setId(rs.getInt("id"));
            proveedor.setNombre(rs.getString("nombre"));
            proveedor.setDireccion(rs.getString("direccion"));
            proveedor.setCorreoElectronico(rs.getString("correo_electronico"));
            proveedor.setNumeroTelefonico(rs.getInt("numero_telefonico"));

            all.add(proveedor);
        }

        return all;
    }

    @Override
    public Proveedor findById(int id) throws SQLException, ValidationModelException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM proveedor WHERE id = " + id);
        if (!rs.next()) {
            return null;
        }
        Proveedor proveedor = new Proveedor();
        proveedor.setId(rs.getInt("id"));
        proveedor.setNombre(rs.getString("nombre"));
        proveedor.setDireccion(rs.getString("direccion"));
        proveedor.setCorreoElectronico(rs.getString("correo_electronico"));
        proveedor.setNumeroTelefonico(rs.getInt("numero_telefonico"));

        return proveedor;
    }

    @Override
    public void update(int id, Proveedor model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
