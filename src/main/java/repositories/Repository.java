package repositories;

import java.util.List;

public interface Repository<Model> {

    public void save(Model model) throws Exception;

    public List<Model> findAll() throws Exception;

    public Model findById(int id) throws Exception;

    public void update(int id, Model model) throws Exception;

    public boolean delete(int id) throws Exception;
}
