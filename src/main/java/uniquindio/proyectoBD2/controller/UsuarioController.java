package uniquindio.proyectoBD2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniquindio.proyectoBD2.entity.Usuario;
import uniquindio.proyectoBD2.service.UsuarioService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5500")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        boolean isValid = usuarioService.validarUsuario(usuario.getCorreo(), usuario.getContrasena());
        if (isValid) {
            return ResponseEntity.ok().build(); // Solo devolvemos 200 OK sin cuerpo
        } else {
            return ResponseEntity.status(400).body("Credenciales incorrectas"); // 400 Bad Request con mensaje
        }
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Void> crearUsuario(@RequestBody Usuario usuario) {
        usuarioService.crearUsuario(usuario.getId(), usuario.getCorreo(), usuario.getContrasena());
        return ResponseEntity.ok().build();
    }
}

