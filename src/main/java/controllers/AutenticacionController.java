package controllers;

import app.Sesion;
import java.sql.Connection;
import java.sql.SQLException;
import models.Usuario;
import org.mindrot.jbcrypt.BCrypt;
import repositories.UsuarioRepository;

public class AutenticacionController {

    private final Connection connection;

    public AutenticacionController(Connection connection) {
        this.connection = connection;
    }

    public boolean login(String username, String password) throws SQLException, RuntimeException {
        UsuarioRepository usuarioRepository = new UsuarioRepository(connection);
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            throw new RuntimeException("El usuario no existe");
        }

        if (!BCrypt.checkpw(password, usuario.getPassword())) {
            throw new RuntimeException("La contraseña es incorrecta");
        }
        
        Sesion.instance().setUsuario(usuario);
        return true;
    }

    public void logout() {

        Sesion.instance().setUsuario(null);

    }

}
