package repositories;

import app.ConexionDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import models.Proveedor;


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
