package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Producto;

public class ProductoRepository implements Repository<Producto> {

    private final Connection connection;

    public ProductoRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Producto producto) throws SQLException {
        int currentId = getLastId() + 1;
        PreparedStatement st = connection.prepareStatement("INSERT INTO producto (id, nombre, descripcion, precio, id_proveedor) VALUES (?, ?, ?, ?, ?)");

        st.setInt(1, currentId);
        st.setString(2, producto.getNombre());
        st.setString(3, producto.getDescripcion());
        st.setDouble(4, producto.getPrecio());
        st.setInt(5, producto.getProveedorId());
        st.executeUpdate();

        producto.setId(currentId);
        System.out.println(producto);
    }

    @Override
    public List<Producto> findAll() {
        List<Producto> all = new ArrayList<>();

        try {
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

        } catch (SQLException ex) {
            System.err.println("Error obteniendo todos los registros de la tabla productos\n" + ex.getMessage());
        }

        return all;
    }

    @Override
    public Producto findById(int id) {
        Producto producto = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM producto WHERE id = " + id);
            rs.next();
            producto = new Producto();
            producto.setId(rs.getInt("id"));
            producto.setNombre(rs.getString("nombre"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setPrecio(rs.getDouble("precio"));
            producto.setProveedorId(rs.getInt("id_proveedor"));
        } catch (SQLException ex) {
            System.err.println("Error finbyid" + ex.getMessage());
        }

        return producto;
    }

    @Override
    public void update(int id, Producto producto) {
         try {
            PreparedStatement st = connection.prepareStatement("UPDATE producto SET nombre = ?, descripcion = ?, precio = ?, id_proveedor = ? WHERE id = ?");
            st.setString(1, producto.getNombre());
            st.setString(2, producto.getDescripcion());
            st.setDouble(3, producto.getPrecio());
            st.setInt(4, producto.getProveedorId());
            st.setInt(5, id);

            st.executeUpdate();
            
             System.out.println("Producto id: " + id + " Actualizado correctamente a : " + producto);
        } catch (SQLException ex) {
            System.err.println("Error finbyid" + ex.getMessage());
        }
    }

    @Override
    public boolean delete(int id) {
        boolean deleted = false;
        try {
            PreparedStatement st = connection.prepareStatement("DELETE FROM producto where id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            deleted = true;
        } catch (SQLException ex) {
            System.err.println("Error eliminando registro" + ex.getMessage());
        }
        return deleted;
    }

    private int getLastId() {
        int id = 0;
        try {
            PreparedStatement st = connection.prepareStatement("SELECT MAX(id) FROM producto");
            ResultSet result = st.executeQuery();
            if (result.next()) {
                id = result.getInt(1);
            }

        } catch (SQLException ex) {
            System.err.println("Error al obtener id:\n" + ex.getMessage());
        }
        return id;
    }
}
