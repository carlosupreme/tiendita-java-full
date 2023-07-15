package shared.domain.infrastructure;

import io.github.cdimascio.dotenv.Dotenv;
import views.MessageHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionMock {

    private static MySQLConnectionMock instance;
    private Connection connection;

    private MySQLConnectionMock() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Dotenv dotenv = Dotenv.configure().load();

            String host = dotenv.get("TEST_DB_HOST");
            String port = dotenv.get("TEST_DB_PORT");
            String database = dotenv.get("TEST_DB_DATABASE");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
            String username = dotenv.get("TEST_DB_USERNAME");
            String password = dotenv.get("TEST_DB_PASSWORD");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            MessageHandler.showErrorMessage("El servidor de MySQL no está funcionando");
        }
    }

    public static MySQLConnectionMock getInstance() {
        if (null == instance) {
            instance = new MySQLConnectionMock();
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
            e.printStackTrace();
        }
    }
}
