package user.infrastructure;

import user.application.RegisterError;
import user.application.UserNotExist;
import user.domain.entities.*;

import java.sql.*;
import java.util.Optional;

public class MySQLUserRepository implements UserRepository {

    private final Connection connection;

    public MySQLUserRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> search(String _username) {
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM usuarios WHERE username = ? LIMIT 1");
            st.setString(1, _username);
            ResultSet rs = st.executeQuery();

            if (!rs.next()) {
                throw new UserNotExist("User not found");
            }

            Password password = new Password(rs.getString("password"));
            UserFullname fullname = new UserFullname(rs.getString("nombre"));
            Username username = new Username(_username);

            return Optional.of(new User(username, password, fullname));
        } catch (SQLException | UserNotExist ex) {
            System.err.println(ex);
            return Optional.empty();
        }
    }

    @Override
    public UserId save(User user) {
        try {
            Username username = user.username();
            Password password = user.password();
            UserFullname fullname = user.fullname();
            String sql = "INSERT INTO usuarios (username, nombre, password) VALUES (?, ?, ?)";

            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, username.value());
            st.setString(2, fullname.value());
            st.setString(3, password.value());

            if (st.executeUpdate() == 0) {
                throw new Exception("No se creó el usuario");
            }

            ResultSet generatedKeys = st.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new Exception("No se obtuvo el ID");
            }

            return new UserId(generatedKeys.getLong(1));
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.err.println(ex.getMessage());
            throw new RegisterError("El nombre de usuario ya existe");
        } catch (Exception ex) {
            System.err.println(ex);
            throw new RegisterError(ex.getMessage());
        }
    }

}
