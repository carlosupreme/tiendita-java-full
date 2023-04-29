package repositories;

import java.sql.SQLException;
import java.util.List;

public interface Repository<Model> {
    public void save(Model model) throws SQLException;
    public List<Model> findAll();
    public Model findById(int id);
    public void update(Model model);
    public boolean delete(int id); 
}
