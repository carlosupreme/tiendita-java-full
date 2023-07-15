/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import shared.infrastructure.MySQLConnection;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Raul
 */
public class TransactionManager {
    private static final Connection conexion = MySQLConnection.getInstance().getConnection(); 
     
    public static void ejecutarTransaccion(InstruccionDML dml) throws SQLException {
        try {
            conexion.setAutoCommit(false); 
            dml.ejecutar();
            conexion.commit();
        } catch (SQLException ex) {
            if (conexion != null) {
                conexion.rollback();
                System.out.println("Rollback exitoso");
            }
            throw ex;
        }  finally {
            conexion.setAutoCommit(true);
        }
    }
}
