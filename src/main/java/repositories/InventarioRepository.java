package repositories;

import db.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.Inventario;

/**
 *
 * @author carlos
 */
public class InventarioRepository {

    private Connection connection;

    public InventarioRepository() {
        this.connection = ConexionDB.getInstance().getConnection();
    }

    public void save(Inventario inventario) throws SQLException {

        String INSERT_QUERY = "INSERT INTO inventario values (?, ?)";
        PreparedStatement st = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);

        st.setLong(1, inventario.getIdProducto());
        st.setLong(2, inventario.getStock());

        if (st.executeUpdate() == 0) {
            throw new SQLException("No se agrego el stock.");
        }
    }

    public long getProductStock(long id) throws SQLException {
        long stock = 0;
        String query = "SELECT stock from inventario where id_producto = ?";

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, id);

            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    stock = resultSet.getLong("stock");
                }
            }
        }

        return stock;
    }
}
