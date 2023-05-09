package db;

import java.lang.reflect.Field;
import java.sql.*;

public class PreparedStatementMapper<T> {

    //private final Connection connection = ConexionDB.getInstance().getConnection();
    private final Connection connection = null;
    private final String tableName;

    public PreparedStatementMapper(String tableName) {
        this.tableName = tableName;
    }

    public int insert(T object) throws SQLException {
        String query = buildInsertQuery(object);
        int rowsAffected;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            setStatementParameters(statement, object);
            rowsAffected = statement.executeUpdate();
        }
        return rowsAffected;
    }

    private void setStatementParameters(PreparedStatement statement, T object) throws SQLException {
        int i = 1;
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
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

    public String buildInsertQuery(T object) {
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (Field field : object.getClass().getDeclaredFields()) {
            if (columns.length() > 0) {
                columns.append(", ");
                values.append(", ");
            }
            columns.append(field.getName());
            values.append("?");
        }
        return String.format("INSERT INTO %s (%s) VALUES (%s)",
                tableName, columns.toString(), values.toString());
    }

}

