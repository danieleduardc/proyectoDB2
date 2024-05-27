package uniquindio.proyectoBD2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/test")
    public ResponseEntity<String> checkHealth() {
        try {
            jdbcTemplate.execute("SELECT 1 FROM DUAL"); // Consulta simple a Oracle
            return ResponseEntity.ok("Conexión a la base de datos exitosa");
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}