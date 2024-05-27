package uniquindio.proyectoBD2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uniquindio.proyectoBD2.entity.Profesor;
import uniquindio.proyectoBD2.repository.ProfesorRepository;

import java.util.List;

@Service
public class ProfesorService {

    private final ProfesorRepository profesorRepository;

    @Autowired
    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public void crearProfesor(Profesor profesor) {
        profesorRepository.crearProfesor(profesor);
    }

    public Profesor leerProfesor(Long id) {
        return profesorRepository.leerProfesor(id);
    }

    public List<Profesor> listarProfesores() {
        return profesorRepository.listarProfesores();
    }

    public void actualizarProfesor(Long id, Profesor profesor) {
        profesor.setId(id);
        profesorRepository.actualizarProfesor(profesor);
    }

    public void eliminarProfesor(Long id) {
        profesorRepository.eliminarProfesor(id);
    }
}
