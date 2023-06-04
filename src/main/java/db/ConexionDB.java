package db;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;
import views.MessageHandler;

public class ConexionDB {

    private static ConexionDB instance = null;
    private Connection conn = null;

    private ConexionDB() {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            Dotenv dotenv = Dotenv.configure().load();

            // Establecer la conexión con la base de datos
            String url = "jdbc:mysql://" + dotenv.get("DB_HOST") + ":" + dotenv.get("DB_PORT") + "/" + dotenv.get("DB_DATABASE");
            String username = dotenv.get("DB_USERNAME");
            String password = dotenv.get("DB_PASSWORD");
            conn = DriverManager.getConnection(url, username, password);

            System.out.println("Conexión establecida.");
        } catch (SQLException | ClassNotFoundException e) {
            MessageHandler.showErrorMessage("El servidor de MySQL no está funcionando");
        }
    }

    public static ConexionDB getInstance() {
        if (instance == null) {
            instance = new ConexionDB();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }

    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

}
