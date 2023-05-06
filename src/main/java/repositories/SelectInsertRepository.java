/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositories;

import exceptions.ValidationModelException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Raul
 */
public interface SelectInsertRepository<Model> {
    
    public void save(Model model) throws SQLException, ValidationModelException;

    public List<Model> findAll() throws SQLException, ValidationModelException;

    public Model findById(int id) throws SQLException, ValidationModelException;
    
}
