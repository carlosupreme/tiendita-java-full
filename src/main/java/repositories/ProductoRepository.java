
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;


import app.ConexionDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import models.Producto;

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
