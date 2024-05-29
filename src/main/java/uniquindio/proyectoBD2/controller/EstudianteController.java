package uniquindio.proyectoBD2.controller;

import org.springframework.http.HttpStatus;
import uniquindio.proyectoBD2.entity.Estudiante;
import uniquindio.proyectoBD2.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiante")
@CrossOrigin(origins = "http://localhost:5500")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    // Crear Estudiante
    @PostMapping
    public ResponseEntity<Void> crearEstudiante(@RequestBody Estudiante estudiante) {
        estudianteService.crearEstudiante(estudiante);
        return ResponseEntity.ok().build();
    }

    // Leer Estudiante
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> leerEstudiante(@PathVariable Long id) {
        Estudiante estudiante = estudianteService.leerEstudiante(id);
        return ResponseEntity.ok(estudiante);
    }

    // Leer todos los Estudiantes
    @GetMapping
    public ResponseEntity<List<Estudiante>> listarEstudiantes() {
        List<Estudiante> estudiantes = estudianteService.listarEstudiantes();
        return ResponseEntity.ok(estudiantes);
    }

    // Actualizar Estudiante
    @PutMapping("/actualizar")
    public ResponseEntity<Void> actualizarEstudiante(@RequestBody Estudiante estudiante) {
        estudianteService.actualizarEstudiante(estudiante);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Eliminar Estudiante
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminarEstudiante(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
