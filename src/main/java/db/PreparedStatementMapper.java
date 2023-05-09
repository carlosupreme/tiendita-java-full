package db;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class PreparedStatementMapper<T> {

    //private final Connection connection = ConexionDB.getInstance().getConnection();
    private final Connection connection = null;
    private final String tableName;

    public PreparedStatementMapper(String tableName) {
        this.tableName = tableName;
    }

    public int insertar(T object) throws SQLException {
        String query = construirSQLInsert(object);
        int filasAfectadas;
        try (PreparedStatement st = connection.prepareStatement(query)) {
            setParametrosPreparados(st, object);
            filasAfectadas = st.executeUpdate();
        }
        return filasAfectadas;
    }

    public String construirSQLInsert(T object) {
        StringBuilder columnas = new StringBuilder();
        StringBuilder valores = new StringBuilder();
        for (Field field : object.getClass().getDeclaredFields()) {
            if (columnas.length() > 0) {
                columnas.append(", ");
                valores.append(", ");
            }
            columnas.append(field.getName()
                    .replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase());
            valores.append("?");
        }
        return String.format("INSERT INTO %s (%s) VALUES (%s)",
                tableName, columnas.toString(), valores.toString());
    }

    private void setParametrosPreparados(PreparedStatement statement, 
            T object) throws SQLException {
        Map<String, Object> map = getValoresAtributos(object);
        
        for (int i = 1; i <= map.size(); i++) {
            Object value = map.get("atributo" + i);
            if (value instanceof String) {
                statement.setString(i, (String) value);
            } else if (value instanceof Integer) {
                statement.setInt(i, (Integer) value);
            } else if (value instanceof Double) {
                statement.setDouble(i, (Double) value);
            } else if (value instanceof Timestamp) {
                statement.setTimestamp(i, (Timestamp) value);
            } else if (value instanceof Date) {
                statement.setDate(i, (Date) value);
            }
        }
    }

    private Map<String, Object> getValoresAtributos(T object) {
        Map<String, Object> map = new HashMap<>();
        int i = 1;
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                map.put("atributo" + i, value);
                i++;
            } catch (IllegalAccessException e) {
                // Ignorar campos inaccesibles
            }
        }
        return map;
    }
}