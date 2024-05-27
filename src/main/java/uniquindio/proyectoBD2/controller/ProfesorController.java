package uniquindio.proyectoBD2.controller;

import uniquindio.proyectoBD2.entity.Profesor;
import uniquindio.proyectoBD2.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesor")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    // Crear Profesor
    @PostMapping
    public ResponseEntity<Void> crearProfesor(@RequestBody Profesor profesor) {
        profesorService.crearProfesor(profesor);
        return ResponseEntity.ok().build();
    }

    // Leer Profesor
    @GetMapping("/{id}")
    public ResponseEntity<Profesor> leerProfesor(@PathVariable Long id) {
        Profesor profesor = profesorService.leerProfesor(id);
        return ResponseEntity.ok(profesor);
    }

    // Leer todos los Profesores
    @GetMapping
    public ResponseEntity<List<Profesor>> listarProfesores() {
        List<Profesor> profesores = profesorService.listarProfesores();
        return ResponseEntity.ok(profesores);
    }

    // Actualizar Profesor
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarProfesor(@PathVariable Long id, @RequestBody Profesor profesor) {
        profesorService.actualizarProfesor(id, profesor);
        return ResponseEntity.ok().build();
    }

    // Eliminar Profesor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProfesor(@PathVariable Long id) {
        profesorService.eliminarProfesor(id);
        return ResponseEntity.ok().build();
    }
}