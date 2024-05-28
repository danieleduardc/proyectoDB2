package uniquindio.proyectoBD2.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uniquindio.proyectoBD2.entity.DetalleExamen;
import uniquindio.proyectoBD2.entity.EstadisticaExamen;
import uniquindio.proyectoBD2.entity.Examen;
import uniquindio.proyectoBD2.repository.ExamenRepository;

import java.util.List;


@Service
public class ExamenService {

    private final ExamenRepository examenRepository;

    @Autowired
    public ExamenService(ExamenRepository examenRepository) {
        this.examenRepository = examenRepository;
    }

    public void crearExamen(Examen examen) {
        examenRepository.crearExamen(examen);
    }

    public Examen leerExamen(Long id) {
        return examenRepository.leerExamen(id);
    }

    public List<Examen> listarExamenes() {
        return examenRepository.listarExamenes();
    }

    public void actualizarExamen(Long id, Examen examen) {
        examen.setId(id);
        examenRepository.actualizarExamen(examen);
    }

    public void eliminarExamen(Long id) {
        examenRepository.eliminarExamen(id);
    }

    public List<DetalleExamen> obtenerDetalleExamen(Long examenId) {
        return examenRepository.obtenerDetalleExamen(examenId);
    }

    public static List<EstadisticaExamen> obtenerEstadisticasExamen(Long examenId) {
        return ExamenRepository.obtenerEstadisticasExamen(examenId);
    }

    public List<Examen> obtenerExamenesPorProfesor(String profesorIdentificacion) {
        return examenRepository.obtenerExamenesPorProfesor(profesorIdentificacion);
    }

    public List<Examen> obtenerExamenesEstudiante(String correo) {
        return examenRepository.obtenerExamenesEstudiante(correo);
    }
}
