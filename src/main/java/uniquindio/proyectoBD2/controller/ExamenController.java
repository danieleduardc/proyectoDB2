package uniquindio.proyectoBD2.controller;

import uniquindio.proyectoBD2.entity.DetalleExamen;
import uniquindio.proyectoBD2.entity.EstadisticaExamen;
import uniquindio.proyectoBD2.entity.Examen;
import uniquindio.proyectoBD2.service.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examen")
@CrossOrigin(origins = "http://localhost:5500")
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    // Crear Examen
    @PostMapping
    public ResponseEntity<Void> crearExamen(@RequestBody Examen examen) {
        examenService.crearExamen(examen);
        return ResponseEntity.ok().build();
    }

    // Leer Examen
    @GetMapping("/{id}")
    public ResponseEntity<Examen> leerExamen(@PathVariable Long id) {
        Examen examen = examenService.leerExamen(id);
        return ResponseEntity.ok(examen);
    }

    // Leer todos los Examenes
    @GetMapping
    public ResponseEntity<List<Examen>> listarExamenes() {
        List<Examen> examenes = examenService.listarExamenes();
        return ResponseEntity.ok(examenes);
    }

    // Actualizar Examen
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarExamen(@PathVariable Long id, @RequestBody Examen examen) {
        examenService.actualizarExamen(id, examen);
        return ResponseEntity.ok().build();
    }

    // Eliminar Examen
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarExamen(@PathVariable Long id) {
        examenService.eliminarExamen(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/detalle")
    public ResponseEntity<List<DetalleExamen>> obtenerDetalleExamen(@PathVariable Long id) {
        List<DetalleExamen> detalleExamen = examenService.obtenerDetalleExamen(id);
        return ResponseEntity.ok(detalleExamen);
    }

    @GetMapping("/{id}/estadisticas")
    public ResponseEntity<List<EstadisticaExamen>> obtenerEstadisticasExamen(@PathVariable Long id) {
        List<EstadisticaExamen> estadisticasExamen = ExamenService.obtenerEstadisticasExamen(id);
        return ResponseEntity.ok(estadisticasExamen);
    }

    @GetMapping("/profesor/{identificacion}")
    public ResponseEntity<List<Examen>> obtenerExamenesPorProfesor(@PathVariable String identificacion) {
        List<Examen> examenes = examenService.obtenerExamenesPorProfesor(identificacion);
        return ResponseEntity.ok(examenes);
    }

    @GetMapping("/estudiante/{correo}")
    public ResponseEntity<List<Examen>> obtenerExamenesEstudiante(@PathVariable String correo) {
        List<Examen> examenes = examenService.obtenerExamenesEstudiante(correo);
        return ResponseEntity.ok(examenes);
    }
}
