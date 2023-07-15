package shared.infrastructure;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;
import views.MessageHandler;

public class MySQLConnection {

    private static MySQLConnection instance;
    private Connection connection;

    private MySQLConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Dotenv dotenv = Dotenv.configure().load();

            String host = dotenv.get("DB_HOST");
            String port = dotenv.get("DB_PORT");
            String database = dotenv.get("DB_DATABASE");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
            String username = dotenv.get("DB_USERNAME");
            String password = dotenv.get("DB_PASSWORD");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            MessageHandler.showErrorMessage("El servidor de MySQL no está funcionando");
        }
    }

    public static MySQLConnection getInstance() {
        if (null == instance) {
            instance = new MySQLConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (null != connection) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
    }

}
