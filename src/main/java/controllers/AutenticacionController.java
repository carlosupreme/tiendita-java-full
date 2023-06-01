package controllers;

import app.Sesion;
import db.ConexionDB;
import exceptions.ValidationModelException;
import java.sql.Connection;
import java.sql.SQLException;
import models.Usuario;
import org.mindrot.jbcrypt.BCrypt;
import repositories.UsuarioRepository;

public class AutenticacionController {

    private final Connection connection;

    public AutenticacionController() {
        this.connection = ConexionDB.getInstance().getConnection();
    }

    public boolean login(String username, String password) throws SQLException, ValidationModelException {
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            throw new ValidationModelException("El usuario no existe");
        }

        if (!BCrypt.checkpw(password, usuario.getPassword())) {
            throw new ValidationModelException("La contraseña es incorrecta");
        }

        Sesion.instance().setUsuario(usuario);
        return true;

    }

    public void logout() {

        Sesion.instance().setUsuario(null);

    }

}
