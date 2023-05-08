package repositories;

import db.ConexionDB;
import exceptions.ValidationModelException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.ModelColumnMap;

public interface Repository<Model> {

    default public void save(Model model) throws SQLException, ValidationModelException {

        String nombreTabla = model.getClass().getSimpleName().toLowerCase();

        ArrayList<String> columnas = ModelColumnMap.getColumns(model.getClass());
        
        String sql = "INSERT INTO " + nombreTabla + " ("
                + String.join(", ", columnas.get(0)) + ") VALUES ("
                + String.join(", ", Collections.nCopies(columnas.size(), "?")) + ")";

        try (PreparedStatement statement = 
                ConexionDB.getInstance().getConnection().prepareStatement(sql)) {
            
            int i = 1;
            for (Object valor : columnas) {
                statement.setObject(i++, valor);
            }

            statement.executeUpdate();
        }

    }

    public List<Model> findAll() throws SQLException, ValidationModelException;

    public Model findById(int id) throws SQLException, ValidationModelException;

    public void update(int id, Model model) throws SQLException;

    public void delete(int id) throws SQLException;
}
