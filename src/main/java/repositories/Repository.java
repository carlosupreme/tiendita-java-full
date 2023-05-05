package repositories;

import exceptions.ValidationModelException;
import java.sql.SQLException;
import java.util.List;

public interface Repository<Model> {

    public void save(Model model) throws SQLException, ValidationModelException;

    public List<Model> findAll() throws SQLException, ValidationModelException;

    public Model findById(int id) throws SQLException, ValidationModelException;

    public void update(int id, Model model) throws SQLException;

    public void delete(int id) throws SQLException;
}
