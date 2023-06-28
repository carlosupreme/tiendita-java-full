package db;

import app.MySQLConnection;
import java.lang.reflect.Field;
import java.sql.*;

public class PreparedStatementMapper<T> {
    
    private boolean idGeneradoIgnorado = false;

    public boolean isIdGeneradoIgnorado() {
        return idGeneradoIgnorado;
    } 

    public void setIdGeneradoIgnorado(boolean idGeneradoIgnorado) {
        this.idGeneradoIgnorado = idGeneradoIgnorado;
    }

    public static String sqlName(String str) {
        String result = "";
        char c = str.charAt(0);
        result = result + Character.toLowerCase(c);
        for (int i = 1; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isUpperCase(ch)) {
                result = result + '_';
                result = result + Character.toLowerCase(ch);
            } else {
                result = result + ch;
            }
        }
        return result;
    }

    private final Connection conexion = MySQLConnection.getInstance().getConnection();
    private final String nombreTabla;

    public PreparedStatementMapper(String tableName) {
        this.nombreTabla = tableName;
    }

    public long[] insertar(T object) throws SQLException, IllegalAccessException {
        String query = getSqlString(object);
        
        long[] valores = new long[2];
        valores[0] = -1;
        
        int filasAfectadas;
        try (PreparedStatement statement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setParametros(statement, object);
            filasAfectadas = statement.executeUpdate();
            
            if(!idGeneradoIgnorado) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        valores[0] = generatedKeys.getLong(1);
                    } else {
                        throw new SQLException("Fallo al insertar. ID no obtenido");
                    }
                }
            }
        }
        valores[1] = filasAfectadas;
        return valores;
    }

    private void setParametros(PreparedStatement statement, T objeto) throws SQLException, IllegalAccessException {
        int i = 1;
        Field[] fields = objeto.getClass().getDeclaredFields();
        for (Field field : fields) {
            
            field.setAccessible(true);
            
            if (field.getName().equalsIgnoreCase("id")) {
                continue;
            }
            
            //System.out.println(field.getName());
            
            Object valorAttr = field.get(objeto);
            statement.setObject(i, valorAttr);
            i++;
        }
    }

    public String getSqlString(T object) {
        StringBuilder columnas = new StringBuilder();
        StringBuilder valores = new StringBuilder();
        for (Field field : object.getClass().getDeclaredFields()) {

            if (field.getName().equalsIgnoreCase("id")) {
                continue;
            }

            if (columnas.length() > 0) {
                columnas.append(", ");
                valores.append(", ");
            }
            columnas.append(sqlName(field.getName()));
            valores.append("?");
        }
        return String.format("INSERT INTO %s (%s) VALUES (%s)",
                nombreTabla, columnas.toString(), valores.toString());
    }

}
