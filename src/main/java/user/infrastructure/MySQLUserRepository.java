package user.infrastructure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import user.domain.entities.Password;
import user.domain.entities.User;
import user.domain.entities.UserRepository;
import user.domain.entities.Username;

public class MySQLUserRepository implements UserRepository {

    private final Connection connection = db.ConexionDB.getInstance().getConnection();

    @Override
    public Optional<User> search(Username username) {
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM usuarios WHERE username = ? LIMIT 1");
            st.setString(1, username.value());
            ResultSet rs = st.executeQuery();

            if (!rs.next()) {
                throw new Exception("User not found");
            }

            return Optional.of(new User(username, new Password(rs.getString("password")), rs.getString("nombre")));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void save(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
