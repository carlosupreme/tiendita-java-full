/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.proyecto.administradortiendita;
import java.sql.*;
/**
 *
 * @author gusam
 */
public class ConexionDB {
    
   private static ConexionDB instance = null;
   private Connection conn = null;
   
   private ConexionDB() {
      try { 
         // Cargar el driver de MySQL
         Class.forName("com.mysql.cj.jdbc.Driver");
         
         // Establecer la conexión con la base de datos
         String url = "jdbc:mysql://localhost:3306/tiendita";
         String username = "root";
         String password = "093iliMmc*";
         conn = DriverManager.getConnection(url, username, password);
         
         System.out.println("Conexión establecida.");
      } catch (SQLException e) {
         System.out.println("Error al conectarse a la base de datos: " + e.getMessage());
      } catch (ClassNotFoundException e) {
         System.out.println("Error al cargar el driver de MySQL: " + e.getMessage());
      }
   }
   
   public static ConexionDB getInstance() {
      if (instance == null) {
         instance = new ConexionDB();
      }
      return instance;
   }
   
   public Connection getConnection() {
      return conn;
   }
   
   public void closeConnection() {
      try {
         if (conn != null) {
            conn.close();
            System.out.println("Conexión cerrada.");
         }
      } catch (SQLException e) {
         System.out.println("Error al cerrar la conexión: " + e.getMessage());
      }
   }

    
}
