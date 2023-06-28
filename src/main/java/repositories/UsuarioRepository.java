package repositories;

import app.MySQLConnection;
import exceptions.ValidationModelException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import models.Usuario;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioRepository implements Repository<Usuario> {

    private final Connection connection;
    private final String INSERT_SQL = "INSERT INTO usuarios (username, nombre, password) VALUES (?, ?, ?)";

    public UsuarioRepository() {
        this.connection = MySQLConnection.getInstance().getConnection();
    }

    @Override
    public void save(Usuario usuario) throws SQLException, ValidationModelException {
        PreparedStatement st = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
        mapUser(usuario, st);

        if (st.executeUpdate() == 0) {
            throw new SQLException("No se creó el usuario.");
        }

        ResultSet generatedKeys = st.getGeneratedKeys();
        if (generatedKeys.next()) {
            usuario.setId(generatedKeys.getInt(1));
        } else {
            throw new SQLException("No se obtuvo el ID");
        }

    }

    @Override
    public List<Usuario> findAll() throws SQLException, ValidationModelException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario findById(int id) throws SQLException, ValidationModelException {
        PreparedStatement st = connection.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();

        if (!rs.next()) {
            return null;
        }

        Usuario usuario = new Usuario();
        mapResultSet(rs, usuario);
        return usuario;
    }

    public Usuario findByUsername(String username) throws SQLException, ValidationModelException {
        PreparedStatement st = connection.prepareStatement("SELECT * FROM usuarios WHERE username = ?");
        st.setString(1, username);
        ResultSet rs = st.executeQuery();

        if (!rs.next()) {
            return null;
        }

        Usuario usuario = new Usuario();
        mapResultSet(rs, usuario);
        return usuario;
    }

    @Override
    public void update(int id, Usuario model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void mapUser(Usuario usuario, PreparedStatement st) throws SQLException {
        st.setString(1, usuario.getUsername());
        st.setString(2, usuario.getNombre());
        st.setString(3, BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt()));

    }

    private void mapResultSet(ResultSet rs, Usuario usuario) throws SQLException {
        usuario.setId(rs.getInt("id"));
        usuario.setUsername(rs.getString("username"));
        usuario.setPassword(rs.getString("password"));
        usuario.setNombre(rs.getString("nombre"));
    }

}
