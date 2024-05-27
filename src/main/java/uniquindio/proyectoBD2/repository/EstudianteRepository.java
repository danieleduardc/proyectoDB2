package uniquindio.proyectoBD2.repository;

import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import uniquindio.proyectoBD2.entity.Estudiante;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository
public class EstudianteRepository {

    private final JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall leerEstudianteCall;
    private SimpleJdbcCall crearEstudianteCall;
    private SimpleJdbcCall leerTodosEstudiantesCall;
    private SimpleJdbcCall actualizarEstudianteCall;
    private SimpleJdbcCall eliminarEstudianteCall;



    @Autowired
    public EstudianteRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;

        this.leerEstudianteCall = new SimpleJdbcCall(dataSource)
                .withFunctionName("leer_estudiante")
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC),
                        new SqlOutParameter("p_resultado", OracleTypes.CURSOR, new EstudianteRowMapper()));

        this.crearEstudianteCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("crear_estudiante")
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC),
                        new SqlParameter("p_nombre", Types.VARCHAR),
                        new SqlParameter("p_apellido", Types.VARCHAR),
                        new SqlParameter("p_correo", Types.VARCHAR),
                        new SqlParameter("p_carrera", Types.VARCHAR),
                        new SqlParameter("p_examen_id", Types.NUMERIC));

        this.leerTodosEstudiantesCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("leer_todos_estudiantes")
                .declareParameters(
                        new SqlOutParameter("p_resultado", OracleTypes.CURSOR, new EstudianteRowMapper()));

        this.actualizarEstudianteCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("actualizar_estudiante")
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC),
                        new SqlParameter("p_nombre", Types.VARCHAR),
                        new SqlParameter("p_apellido", Types.VARCHAR),
                        new SqlParameter("p_correo", Types.VARCHAR),
                        new SqlParameter("p_carrera", Types.VARCHAR),
                        new SqlParameter("p_examen_id", Types.NUMERIC));

        this.eliminarEstudianteCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("eliminar_estudiante")
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC));
    }

    public void crearEstudiante(Estudiante estudiante) {
        crearEstudianteCall.execute(estudiante.getId(), estudiante.getNombre(), estudiante.getApellido(), estudiante.getCorreo(), estudiante.getCarrera(), estudiante.getExamenId());
    }

    public Estudiante leerEstudiante(Long id) {
        Map<String, Object> result = leerEstudianteCall.execute(id);
        return ((List<Estudiante>) result.get("p_resultado")).get(0);
    }

    public List<Estudiante> listarEstudiantes() {
        Map<String, Object> result = leerTodosEstudiantesCall.execute();
        return (List<Estudiante>) result.get("p_resultado");
    }

    public void actualizarEstudiante(Estudiante estudiante) {
        actualizarEstudianteCall.execute(estudiante.getId(), estudiante.getNombre(), estudiante.getApellido(), estudiante.getCorreo(), estudiante.getCarrera(), estudiante.getExamenId());
    }

    public void eliminarEstudiante(Long id) {
        eliminarEstudianteCall.execute(id);
    }

    private static final class EstudianteRowMapper implements RowMapper<Estudiante> {
        @Override
        public Estudiante mapRow(ResultSet rs, int rowNum) throws SQLException {
            Estudiante estudiante = new Estudiante();
            estudiante.setId(rs.getLong("id"));
            estudiante.setNombre(rs.getString("nombre"));
            estudiante.setApellido(rs.getString("apellido"));
            estudiante.setCorreo(rs.getString("correo"));
            estudiante.setCarrera(rs.getString("carrera"));
            estudiante.setExamenId(rs.getLong("examen_id"));
            return estudiante;
        }
    }



}
