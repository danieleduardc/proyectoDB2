package uniquindio.proyectoBD2.service;

import uniquindio.proyectoBD2.entity.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uniquindio.proyectoBD2.repository.EstudianteRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public void crearEstudiante(Estudiante estudiante) {
        estudianteRepository.crearEstudiante(estudiante);
    }

    public Estudiante leerEstudiante(Long id) {
        return estudianteRepository.leerEstudiante(id);
    }

    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.listarEstudiantes();
    }

    public void actualizarEstudiante(Estudiante estudiante) {
        estudianteRepository.actualizarEstudiante(estudiante);
    }

    public void eliminarEstudiante(Long id) {
        estudianteRepository.eliminarEstudiante(id);
    }
}
