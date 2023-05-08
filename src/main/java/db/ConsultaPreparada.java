/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

/**
 *
 * @author Raul
 */
import db.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaPreparada {

    private Connection conexion = ConexionDB.getInstance().getConnection();

    public <T> List<T> ejecutarConsulta(String sql, Object[] parametros, ArrayList<T> mapper) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> resultados = new ArrayList<>();

        try {
            ps = conexion.prepareStatement(sql);

            if (parametros != null) {
                for (int i = 0; i < parametros.length; i++) {
                    ps.setObject(i + 1, parametros[i]);
                }
            }

            rs = ps.executeQuery();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return resultados;
    }
}

