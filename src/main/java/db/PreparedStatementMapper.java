package db;

import java.lang.reflect.Field;
import java.sql.*;

public class PreparedStatementMapper<T> {

    //private final Connection connection = ConexionDB.getInstance().getConnection();
    private final Connection conexion = null;
    private final String nombreTabla;

    public PreparedStatementMapper(String tableName) {
        this.nombreTabla = tableName;
    }

    public int insertar(T object) throws SQLException {
        String query = getSqlString(object);
        int filasAfectadas;
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            setParametros(statement, object);
            filasAfectadas = statement.executeUpdate();
        }
        return filasAfectadas;
    }

    private void setParametros(PreparedStatement statement, T objeto) throws SQLException {
        int i = 1;
        Field[] fields = objeto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(objeto);
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
                i++;
            } catch (IllegalAccessException e) {
                // Ignorar campos inaccesibles
            }
        }
    }

    public String getSqlString(T object) {
        StringBuilder columnas = new StringBuilder();
        StringBuilder valores = new StringBuilder();
        for (Field field : object.getClass().getDeclaredFields()) {
            if (columnas.length() > 0) {
                columnas.append(", ");
                valores.append(", ");
            }
            columnas.append(field.getName());
            valores.append("?");
        }
        return String.format("INSERT INTO %s (%s) VALUES (%s)",
                nombreTabla, columnas.toString(), valores.toString());
    }

}

