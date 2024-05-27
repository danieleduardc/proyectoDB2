package uniquindio.proyectoBD2.controller;

import uniquindio.proyectoBD2.entity.Pregunta;
import uniquindio.proyectoBD2.service.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pregunta")
public class PreguntaController {

    @Autowired
    private PreguntaService preguntaService;

    // Crear Pregunta
    @PostMapping
    public ResponseEntity<Void> crearPregunta(@RequestBody Pregunta pregunta) {
        preguntaService.crearPregunta(pregunta);
        return ResponseEntity.ok().build();
    }

    // Leer Pregunta
    @GetMapping("/{id}")
    public ResponseEntity<Pregunta> leerPregunta(@PathVariable Long id) {
        Pregunta pregunta = preguntaService.leerPregunta(id);
        return ResponseEntity.ok(pregunta);
    }

    // Leer todas las Preguntas
    @GetMapping
    public ResponseEntity<List<Pregunta>> listarPreguntas() {
        List<Pregunta> preguntas = preguntaService.listarPreguntas();
        return ResponseEntity.ok(preguntas);
    }

    // Actualizar Pregunta
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarPregunta(@PathVariable Long id, @RequestBody Pregunta pregunta) {
        preguntaService.actualizarPregunta(id, pregunta);
        return ResponseEntity.ok().build();
    }

    // Eliminar Pregunta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPregunta(@PathVariable Long id) {
        preguntaService.eliminarPregunta(id);
        return ResponseEntity.ok().build();
    }
}
