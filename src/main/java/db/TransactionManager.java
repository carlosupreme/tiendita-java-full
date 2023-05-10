/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Raul
 */
public class TransactionManager {
    private static final Connection conexion = ConexionDB.getInstance().getConnection(); 
     
    public static void ejecutarTransaccion(InstruccionDML dml) throws SQLException {
        try {
            dml.ejecutar();
            conexion.commit();
        } catch (SQLException ex) {
            if (conexion != null) {
                conexion.rollback();
            }
            throw ex;
        } 
    }
}
