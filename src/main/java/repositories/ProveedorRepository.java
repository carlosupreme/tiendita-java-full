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
public class ProveedorRepository implements Repository<Proveedor> {

    private final Connection connection;

    public ProveedorRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Proveedor proveedor) throws SQLException, ValidationModelException {
        int currentId = getLastId() + 1;
        PreparedStatement st;
        st = connection.prepareStatement("INSERT INTO proveedor (id, nombre, direccion, correo_electronico, numero_telefonico) VALUES (?, ?, ?, ?, ?)");
        st.setInt(1, currentId);
        st.setString(2, proveedor.getNombre());
        st.setString(3, proveedor.getDireccion());
        st.setString(4, proveedor.getCorreoElectronico());
        st.setInt(5, proveedor.getNumeroTelefonico());
        st.executeUpdate();

        proveedor.setId(currentId);
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(int id, Proveedor model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private int getLastId() throws SQLException {
        PreparedStatement st = connection.prepareStatement("SELECT MAX(id) FROM proveedor");
        ResultSet result = st.executeQuery();
        if (result.next()) {
            return result.getInt(1);
        }

        return 0;
    }

}
