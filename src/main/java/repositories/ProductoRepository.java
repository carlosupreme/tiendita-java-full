package repositories;

import shared.infrastructure.MySQLConnection;
import exceptions.ValidationModelException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Producto;

public class ProductoRepository {

    private final Connection connection;
    private final String INSERT_QUERY;

    public ProductoRepository() {
        connection = MySQLConnection.getInstance().getConnection();
        INSERT_QUERY = "INSERT INTO productos (nombre, codigo_barras, precio_publico, costo, id_proveedor, categoria) values (?, ?, ?, ?, ?, ?)";
    }

    public boolean isActive(long id) throws SQLException {
        PreparedStatement st = connection.prepareStatement("SELECT activo FROM productos WHERE id = ?");
        st.setLong(1, id);
        ResultSet rs = st.executeQuery();

        if (!rs.next()) {
            return false;
        }

        return rs.getBoolean("activo");
    }

    public void save(Producto producto) throws SQLException, ValidationModelException {
        PreparedStatement st = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, producto.getNombre());
        st.setString(2, producto.getCodigoBarras());
        st.setDouble(3, producto.getPrecioPublico());
        st.setDouble(4, producto.getCosto());
        st.setLong(5, producto.getIdProveedor());
        st.setString(6, producto.getCategoria());

        if (st.executeUpdate() == 0) {
            throw new SQLException("No se creó el producto.");
        }

        ResultSet generatedKeys = st.getGeneratedKeys();
        if (generatedKeys.next()) {
            producto.setId(generatedKeys.getLong(1));
        } else {
            throw new SQLException("No se obtuvo el ID");
        }
    }

    public List<Producto> findAll(boolean showDeleted) throws SQLException, ValidationModelException {
        ArrayList<Producto> all = new ArrayList<>();
        String query = "SELECT * FROM productos";

        if (!showDeleted) {
            query += " WHERE activo = 1";
        }

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Producto producto = new Producto();
            mapResultSet(rs, producto);

            all.add(producto);
        }

        return all;
    }

    public List<Producto> findByCriteria(ProductoCriteria criteria) throws SQLException, ValidationModelException {
        ArrayList<Producto> all = new ArrayList<>();
        String query = "SELECT * FROM productos WHERE activo = 1";

        query += " AND nombre like '%" + criteria.nombre + "%'";
        query += " AND categoria like '%" + criteria.categoria + "%'";
        query += " AND codigo_barras like '%" + criteria.codigoBarras + "%'";
        query += " AND precio_publico like '%" + criteria.precioPublico + "%'";
        query += " AND costo like '%" + criteria.costo + "%'";
        query += " AND id_proveedor like '%" + criteria.idProveedor + "%'";

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Producto producto = new Producto();
            mapResultSet(rs, producto);

            all.add(producto);
        }

        return all;
    }

    public Producto findById(long id) throws SQLException, ValidationModelException {
        PreparedStatement st = connection.prepareStatement("SELECT * FROM productos WHERE id = ?");
        st.setLong(1, id);
        ResultSet rs = st.executeQuery();

        if (!rs.next()) {
            return null;
        }

        Producto producto = new Producto();
        mapResultSet(rs, producto);

        return producto;
    }

    public void update(long id, Producto producto) throws SQLException {
        PreparedStatement st = connection.prepareStatement("UPDATE productos SET id_proveedor = ?, nombre = ?, codigo_barras = ?, precio_publico = ?, costo = ?, categoria = ? WHERE id = ?");
        mapProducto(producto, st);
        st.setLong(7, id);

        st.executeUpdate();
    }

    public void delete(long id) throws SQLException {
        PreparedStatement st = connection.prepareStatement("UPDATE productos SET activo = 0 WHERE id = ? LIMIT 1");
        st.setLong(1, id);
        st.executeUpdate();
    }

    private void mapResultSet(ResultSet rs, Producto producto) throws SQLException {
        producto.setId(rs.getLong("id"));
        producto.setIdProveedor(rs.getLong("id_proveedor"));
        producto.setNombre(rs.getString("nombre"));
        producto.setCodigoBarras(rs.getString("codigo_barras"));
        producto.setPrecioPublico(rs.getDouble("precio_publico"));
        producto.setCosto(rs.getDouble("costo"));
        producto.setCategoria(rs.getString("categoria"));

    }

    private void mapProducto(Producto producto, PreparedStatement st) throws SQLException {
        st.setLong(1, producto.getIdProveedor());
        st.setString(2, producto.getNombre());
        st.setString(3, producto.getCodigoBarras());
        st.setDouble(4, producto.getPrecioPublico());
        st.setDouble(5, producto.getCosto());
        st.setString(6, producto.getCategoria());
    }

}
