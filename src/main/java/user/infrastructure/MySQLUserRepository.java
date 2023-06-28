package user.infrastructure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Optional;
import user.application.RegisterError;
import user.application.UserNotExist;
import user.domain.entities.Password;
import user.domain.entities.User;
import user.domain.entities.UserFullname;
import user.domain.entities.UserId;
import user.domain.entities.UserRepository;
import user.domain.entities.Username;

public class MySQLUserRepository implements UserRepository {

    private final Connection connection = db.ConexionDB.getInstance().getConnection();

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
            System.err.println(ex.toString());
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
            System.err.println(ex.toString());
            throw new RegisterError(ex.getMessage());
        }
    }

}
