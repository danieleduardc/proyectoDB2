package uniquindio.proyectoBD2.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uniquindio.proyectoBD2.entity.Pregunta;
import uniquindio.proyectoBD2.repository.PreguntaRepository;

import java.util.List;

@Service
public class PreguntaService {

    private final PreguntaRepository preguntaRepository;

    @Autowired
    public PreguntaService(PreguntaRepository preguntaRepository) {
        this.preguntaRepository = preguntaRepository;
    }

    public void crearPregunta(Pregunta pregunta) {
        preguntaRepository.crearPregunta(pregunta);
    }

    public Pregunta leerPregunta(Long id) {
        return preguntaRepository.leerPregunta(id);
    }

    public List<Pregunta> listarPreguntas() {
        return preguntaRepository.listarPreguntas();
    }

    public void actualizarPregunta(Long id, Pregunta pregunta) {
        pregunta.setId(id);
        preguntaRepository.actualizarPregunta(pregunta);
    }

    public void eliminarPregunta(Long id) {
        preguntaRepository.eliminarPregunta(id);
    }
}
