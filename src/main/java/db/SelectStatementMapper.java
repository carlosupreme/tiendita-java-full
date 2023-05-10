/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raul
 */
public class SelectStatementMapper<T> {

    private final Connection conexion = ConexionDB.getInstance().getConnection();
    private final String nombreTabla;

    public SelectStatementMapper(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public List<T> selectAll(Class<T> clazz) throws SQLException, 
            IllegalArgumentException, IllegalAccessException, NoSuchMethodException, 
            NoSuchMethodException, InstantiationException, InstantiationException, 
            InvocationTargetException {
        List<T> objetos = new ArrayList<>();
        String query = String.format("SELECT * FROM %s", nombreTabla);
        try (PreparedStatement statement = conexion.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                T objeto = crearObjDesdeResultSet(resultSet, clazz);
                objetos.add(objeto);
            }
        }
        return objetos;
    }

    private T crearObjDesdeResultSet(ResultSet resultSet, Class<T> clazz) 
            throws SQLException, IllegalArgumentException, IllegalAccessException, 
            NoSuchMethodException, InstantiationException, InvocationTargetException {

        T objeto = clazz.getDeclaredConstructor().newInstance();
        for (Field field : clazz.getDeclaredFields()) {
            String nombreAttr = field.getName();
            field.setAccessible(true);
            Object valor = resultSet.getObject(nombreAttr);
            field.set(objeto, valor);
        }

        return objeto;
    }
}
