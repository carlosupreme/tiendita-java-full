package repositories;

import exceptions.ValidationModelException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Producto;

public class ProductoRepository implements CrudRepository<Producto> {

    private final Connection connection;

    public ProductoRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Producto producto) throws SQLException, ValidationModelException {
        PreparedStatement st = connection.prepareStatement("INSERT INTO producto (nombre, descripcion, precio, id_proveedor) VALUES (?, ?, ?, ?)");
        st.setString(1, producto.getNombre());
        st.setString(2, producto.getDescripcion());
        st.setDouble(3, producto.getPrecio());
        st.setInt(4, producto.getProveedorId());

        if (st.executeUpdate() == 0) {
            throw new SQLException("No se creó el producto.");
        }

        System.out.println(producto);
    }

    @Override
    public List<Producto> findAll() throws SQLException, ValidationModelException {
        List<Producto> all = new ArrayList<>();

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM producto");

        while (rs.next()) {
            Producto producto = new Producto();
            producto.setId(rs.getInt("id"));
            producto.setNombre(rs.getString("nombre"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setPrecio(rs.getDouble("precio"));
            producto.setProveedorId(rs.getInt("id_proveedor"));

            all.add(producto);
        }

        return all;
    }

    @Override
    public Producto findById(int id) throws SQLException, ValidationModelException {
        PreparedStatement st = connection.prepareStatement("SELECT * FROM producto WHERE id = ?");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (!rs.next()) {
            return null;
        }
        Producto producto = new Producto();
        producto.setId(rs.getInt("id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setPrecio(rs.getDouble("precio"));
        producto.setProveedorId(rs.getInt("id_proveedor"));

        return producto;
    }

    @Override
    public void update(int id, Producto producto) throws SQLException {

        PreparedStatement st = connection.prepareStatement("UPDATE producto SET nombre = ?, descripcion = ?, precio = ?, id_proveedor = ? WHERE id = ?");
        st.setString(1, producto.getNombre());
        st.setString(2, producto.getDescripcion());
        st.setDouble(3, producto.getPrecio());
        st.setInt(4, producto.getProveedorId());
        st.setInt(5, id);

        st.executeUpdate();

        System.out.println("Producto id: " + id + " Actualizado correctamente a : " + producto);
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement("DELETE FROM producto WHERE id = ? LIMIT 1");
        st.setInt(1, id);
        st.executeUpdate();
    }
}
