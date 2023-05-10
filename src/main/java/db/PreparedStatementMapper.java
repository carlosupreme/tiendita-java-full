package db;

import java.lang.reflect.Field;
import java.sql.*;

public class PreparedStatementMapper<T> {

    private final Connection conexion = ConexionDB.getInstance().getConnection();
    private final String nombreTabla;

    public PreparedStatementMapper(String tableName) {
        this.nombreTabla = tableName;
    }

    public int insertar(T object) throws SQLException, IllegalAccessException {
        String query = getSqlString(object);
        int filasAfectadas;
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            setParametros(statement, object);
            filasAfectadas = statement.executeUpdate();
        }
        return filasAfectadas;
    }

    private void setParametros(PreparedStatement statement, T objeto) throws SQLException, IllegalAccessException {
        int i = 1;
        Field[] fields = objeto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object valorAttr = field.get(objeto);
            statement.setObject(i, valorAttr);
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
