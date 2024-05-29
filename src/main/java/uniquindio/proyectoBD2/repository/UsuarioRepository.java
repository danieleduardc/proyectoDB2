package uniquindio.proyectoBD2.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uniquindio.proyectoBD2.entity.Usuario;

@Repository
public class UsuarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean validarUsuario(String correo, String contrasena) {
        String sql = "SELECT COUNT(*) FROM Usuario WHERE correo = ? AND contrasena = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{correo, contrasena}, Integer.class);
        return count != null && count > 0;
    }

    public void crearUsuario(Long id, String correo, String contrasena) {
        String sql = "CALL crearUsuario(?, ?, ?)";
        jdbcTemplate.update(sql, id, correo, contrasena);
    }
}
