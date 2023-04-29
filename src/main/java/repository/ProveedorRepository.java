/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import edu.proyecto.administradortiendita.ConexionDB;
import edu.proyecto.administradortiendita.model.Proveedor;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author ili
 */
public class ProveedorRepository {
    
    private static int currentId =0; 
    
    public void save(Proveedor proveedor) throws SQLException{
        Connection conexion = ConexionDB.getInstance().getConnection();
        Statement st = conexion.createStatement();
        
       st.executeUpdate("INSERT INTO proveedor "+ "VALUES("+currentId+", '"+proveedor.getNombre()+"', '"+proveedor.getDireccion()+"', '"+proveedor.getCorreoElectronico()+"', "+proveedor.getNumeroTelefonico()+")");
       ProveedorRepository.currentId +=1;
       
    }
    
}
