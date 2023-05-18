/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
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

    public SelectStatementMapper() {
        this.nombreTabla = null;
    }

    public T selectOne(Class<T> clazz, String sql, String id) throws SQLException,
            IllegalArgumentException, IllegalAccessException, NoSuchMethodException,
            NoSuchMethodException, InstantiationException, InstantiationException,
            InvocationTargetException {
        T objeto = null;
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, id);
        try (statement; ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                objeto = crearObjDesdeResultSet(resultSet, clazz);
            }
        }
        return objeto;
    }

    public List<T> selectAll(Class<T> clazz, String sql) throws SQLException,
            IllegalArgumentException, IllegalAccessException, NoSuchMethodException,
            NoSuchMethodException, InstantiationException, InstantiationException,
            InvocationTargetException {
        List<T> objetos = new ArrayList<>();
        String query;
        if (sql != null) {
            query = sql;
        } else {
            query = String.format("SELECT * FROM %s", nombreTabla);
        }
        try (PreparedStatement statement = conexion.prepareStatement(query); 
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                T objeto = crearObjDesdeResultSet(resultSet, clazz);
                objetos.add(objeto);
            }
        }
        return objetos;
    }

    public String[][] selectAllAsArray(Class<T> clazz, String sql)
            throws SQLException,
            IllegalArgumentException, IllegalAccessException, NoSuchMethodException,
            NoSuchMethodException, InstantiationException, InstantiationException,
            InvocationTargetException {

        ArrayList<String[]> valoresRegistros = new ArrayList<>();
        String query;
        if (sql != null) {
            query = sql;
        } else {
            query = String.format("SELECT * FROM %s", nombreTabla);
        }
        try (PreparedStatement statement = conexion.prepareStatement(query); 
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String[] valores;

                valores = obtenerValoresAttr(resultSet, clazz);

                valoresRegistros.add(valores);
            }
        }

        return valoresRegistros.toArray(String[][]::new);
    }

    private T crearObjDesdeResultSet(ResultSet resultSet, Class<T> clazz)
            throws SQLException, IllegalArgumentException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {

        T objeto = clazz.getDeclaredConstructor().newInstance();

        for (Field field : clazz.getDeclaredFields()) {
            String nombreAttr = field.getName();

            field.setAccessible(true);
            Object valor = resultSet.getObject(PreparedStatementMapper.sqlName(nombreAttr));
            field.set(objeto, valor);
        }

        Class<?> currentClass = clazz;
        while (currentClass.getSuperclass() != null) {
            currentClass = currentClass.getSuperclass();
            for (Field field : currentClass.getDeclaredFields()) {
                String nombreAttr = field.getName();

                field.setAccessible(true);
                Object valor = resultSet.getObject(PreparedStatementMapper.sqlName(nombreAttr));
                if (valor instanceof BigDecimal) {
                    BigDecimal decimal = (BigDecimal) valor;
                    field.setDouble(objeto, decimal.doubleValue());
                } else {
                    field.set(objeto, valor);
                }
            }
        }

        return objeto;
    }

    private String[] obtenerValoresAttr(ResultSet resultSet, Class<T> clazz)
            throws SQLException, IllegalArgumentException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {

        Field[] fields = clazz.getDeclaredFields();
        String[] valores = new String[fields.length];
        int i = 0;

        for (Field field : fields) {

            String nombreAttr = field.getName();

            field.setAccessible(true);
            Object valor = resultSet.getObject(PreparedStatementMapper.sqlName(nombreAttr));

            valores[i] = valor.toString();
            i++;
        }

        return valores;
    }
}
