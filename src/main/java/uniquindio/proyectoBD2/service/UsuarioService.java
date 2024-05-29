package uniquindio.proyectoBD2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uniquindio.proyectoBD2.entity.Usuario;
import uniquindio.proyectoBD2.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean validarUsuario(String correo, String contrasena) {
        return usuarioRepository.validarUsuario(correo, contrasena);
    }

    public void crearUsuario(Long id, String correo, String contrasena) {
        usuarioRepository.crearUsuario(id, correo, contrasena);
    }
}
