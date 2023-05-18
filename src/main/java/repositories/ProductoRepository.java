package repositories;

import db.ConexionDB;
import exceptions.ValidationModelException;
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
    private final String INSERT_QUERY;
    
    public ProductoRepository() {
        connection = ConexionDB.getInstance().getConnection();
        INSERT_QUERY = "INSERT INTO productos (nombre, descripcion, codigo_barras, precio_publico, costo, id_proveedor, categoria, marca) values (?, ?, ?, ?, ?, ?, ?, ?)";
    }
    
    @Override
    public void save(Producto producto) throws SQLException, ValidationModelException {
        PreparedStatement st = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
        
        mapProducto(producto, st);
        
        if (st.executeUpdate() == 0) {
            throw new SQLException("No se creó el producto.");
        }
        
        ResultSet generatedKeys = st.getGeneratedKeys();
        if (generatedKeys.next()) {
            producto.setId(generatedKeys.getInt(1));
        } else {
            throw new SQLException("No se obtuvo el ID");
        }
    }
    
    @Override
    public List<Producto> findAll() throws SQLException, ValidationModelException {
        ArrayList<Producto> all = new ArrayList<>();
        
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM productos");
        
        while (rs.next()) {
            Producto producto = new Producto();
            mapResultSet(rs, producto);
            
            all.add(producto);
        }
        
        return all;
    }
    
    @Override
    public Producto findById(int id) throws SQLException, ValidationModelException {
        PreparedStatement st = connection.prepareStatement("SELECT * FROM productos WHERE id = ?");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        
        if (!rs.next()) {
            return null;
        }
        
        Producto producto = new Producto();
        mapResultSet(rs, producto);
        
        return producto;
    }
    
    @Override
    public void update(int id, Producto producto) throws SQLException {
        PreparedStatement st = connection.prepareStatement("UPDATE productos SET id_proveedor = ?, nombre = ?, codigo_barras = ?, precio_publico = ?, costo = ?, fecha_caducidad = ?, categoria = ?, marca = ?, edicion = ?  WHERE id = ?");
        mapProducto(producto, st);
        st.setInt(10, id);
        
        st.executeUpdate();
    }
    
    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement("DELETE FROM productos WHERE id = ? LIMIT 1");
        st.setInt(1, id);
        st.executeUpdate();
    }
    
    private void mapResultSet(ResultSet rs, Producto producto) throws SQLException {
        producto.setId(rs.getInt("id"));
        producto.setProveedorId(rs.getInt("id_proveedor"));
        producto.setNombre(rs.getString("nombre"));
        producto.setCodigoBarras(rs.getString("codigo_barras"));
        producto.setPrecioPublico(rs.getDouble("precio_publico"));
        producto.setCosto(rs.getDouble("costo"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setCategoria(rs.getString("categoria"));
        producto.setMarca(rs.getString("marca"));
        
    }
    
    private void mapProducto(Producto producto, PreparedStatement st) throws SQLException {
        st.setString(1, producto.getNombre());
        st.setString(2, producto.getDescripcion());
        st.setString(3, producto.getCodigoBarras());
        st.setDouble(4, producto.getPrecioPublico());
        st.setDouble(5, producto.getCosto());
        st.setInt(6, producto.getProveedorId());
        st.setString(7, producto.getCategoria());
        st.setString(8, producto.getMarca());
        
    }
    
}
