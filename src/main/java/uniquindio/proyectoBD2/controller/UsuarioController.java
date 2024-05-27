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
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        boolean isValid = usuarioService.validarUsuario(usuario.getCorreo(), usuario.getContrasena());
        if (isValid) {
            return ResponseEntity.ok("Login exitoso");
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}

