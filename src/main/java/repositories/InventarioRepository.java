package repositories;

import db.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
