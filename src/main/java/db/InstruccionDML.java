/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.SQLException;

/**
 *
 * @author Raul
 */
@FunctionalInterface
public interface InstruccionDML {
    void ejecutar() throws SQLException;
}
