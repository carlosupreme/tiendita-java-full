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
import java.util.HashMap;
import java.util.List;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 *
 * @author Raul
 */
public class SelectStatementMapper<T> {

    private final Connection conexion = ConexionDB.getInstance().getConnection();
    private String nombreTabla;
    private String sql;
    public boolean buscandoUsuario = false;
    private HashMap<String, String> mapeoAtributos = new HashMap<>();

    public HashMap<String, String> getMapeoAtributos() {
        return mapeoAtributos;
    }

    public void setMapeoAtributos(HashMap<String, String> mapeoAtributos) {
        this.mapeoAtributos = mapeoAtributos;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
        nombreTabla = null;
    }

    public SelectStatementMapper(String nombreTabla) {
        this.nombreTabla = nombreTabla;
        sql = String.format("SELECT * FROM %s", nombreTabla);
    }

    public SelectStatementMapper() {
    }

    public T findById(Class<T> clazz, String id) throws SQLException,
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

    public List<T> selectAll(Class<T> clazz) throws SQLException,
            IllegalArgumentException, IllegalAccessException, NoSuchMethodException,
            NoSuchMethodException, InstantiationException, InstantiationException,
            InvocationTargetException {
        List<T> objetos = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                T objeto = crearObjDesdeResultSet(resultSet, clazz);
                objetos.add(objeto);
            }
        }
        return objetos;
    }

    private void llenarCamposDeClase(ResultSet resultSet, Class<?> clazz, T objeto)
            throws IllegalAccessException, SQLException {
        for (Field field : clazz.getDeclaredFields()) {
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

    private T crearObjDesdeResultSet(ResultSet resultSet, Class<T> clazz)
            throws SQLException, IllegalArgumentException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        Class<?> currentClass = clazz;
        T objeto = clazz.getDeclaredConstructor().newInstance();

        do {
            llenarCamposDeClase(resultSet, currentClass, objeto);
        } while ((currentClass = currentClass.getSuperclass()) != null);

        return objeto;
    }

    public String[][] selectAllAsArray(Class<T> clazz, String[] adicionales)
            throws SQLException,
            IllegalArgumentException, IllegalAccessException, NoSuchMethodException,
            NoSuchMethodException, InstantiationException, InstantiationException,
            InvocationTargetException {

        ArrayList<String[]> valoresRegistros = new ArrayList<>();
        try (PreparedStatement statement = conexion.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String[] valoresAtributos;

                valoresAtributos = obtenerValoresAttr(resultSet, clazz, adicionales);

                valoresRegistros.add(valoresAtributos);
            }
        }

        return valoresRegistros.toArray(String[][]::new);
    }

    private String[] obtenerValoresAttr(ResultSet resultSet, Class<T> clazz, String[] adicionales)
            throws SQLException, IllegalArgumentException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {

        Field[] fields = clazz.getDeclaredFields();
        String[] valores = new String[fields.length + adicionales.length];
        int i = 0;

        for (Field field : fields) {

            String nombreAttr = field.getName();
            field.setAccessible(true);

            if (buscandoUsuario && nombreAttr.equals("usuarioId")) {

                String sql2 = "SELECT username FROM usuarios WHERE id = ?";

                // Crear un objeto PreparedStatement para evitar ataques de inyección SQL
                PreparedStatement stmt = conexion.prepareStatement(sql2);

                long id = resultSet.getLong(PreparedStatementMapper.sqlName(nombreAttr));

                stmt.setLong(1, id); // Establecer el ID del usuario en el parámetro de la sentencia

                // Ejecutar la consulta
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String username = rs.getString("username");
                    valores[i] = username;
                    i++;
                } else {
                    System.out.println("Usuario no encontrado.");
                }

                continue;

            }

            Object valor;

            if (field.getType().toString().equals("class java.time.Instant")) {
                OffsetDateTime odt = resultSet.getObject(PreparedStatementMapper.sqlName(nombreAttr),
                        OffsetDateTime.class);
                valor = odt.toInstant();
            } else {
                valor = resultSet.getObject(PreparedStatementMapper.sqlName(nombreAttr));
            }

            /*if (mapeoAtributos.containsKey(nombreAttr)) {
                valores[i] = mapeoAtributos.get(nombreAttr);
            }*/
            if (valor instanceof Instant) {
                Instant fechaSQL = (Instant) valor;
                ZonedDateTime zdt = fechaSQL.atZone(ZoneId.of("UTC-6"));
                valores[i] = zdt.toString();
            } else {
                valores[i] = valor.toString();
            }
            i++;
        }

        for (String str : adicionales) {
            valores[i] = str;
            i++;
        }

        return valores;
    }
}
