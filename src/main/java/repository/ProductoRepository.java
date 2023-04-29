package repository;

import edu.proyecto.administradortiendita.ConexionDB;
import edu.proyecto.administradortiendita.model.Producto;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductoRepository {
    //set the id from sql last register
    private static int currentId = 0;
    
    public void save(Producto producto) throws SQLException{
      Connection conexion = ConexionDB.getInstance().getConnection(); 
      Statement st =  conexion.createStatement();
      
      st.executeUpdate("INSERT INTO producto " + "VALUES ("+currentId+", '"+ producto.getNombre()+"', '"+ producto.getDescripcion()+"', "+ producto.getPrecio()+ ", "+ producto.getProveedorId() +")");
      ProductoRepository.currentId += 1;
    }
}
