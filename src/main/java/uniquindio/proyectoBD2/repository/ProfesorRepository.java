package uniquindio.proyectoBD2.repository;

import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import uniquindio.proyectoBD2.entity.Profesor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository
public class ProfesorRepository {

    private final JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall crearProfesorCall;
    private SimpleJdbcCall leerProfesorCall;
    private SimpleJdbcCall leerTodosProfesoresCall;
    private SimpleJdbcCall actualizarProfesorCall;
    private SimpleJdbcCall eliminarProfesorCall;

    @Autowired
    public ProfesorRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;

        this.crearProfesorCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("crear_profesor")
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC),
                        new SqlParameter("p_identificacion", Types.VARCHAR),
                        new SqlParameter("p_nombre", Types.VARCHAR),
                        new SqlParameter("p_apellido", Types.VARCHAR),
                        new SqlParameter("p_correo", Types.VARCHAR),
                        new SqlParameter("p_carrera", Types.VARCHAR),
                        new SqlParameter("p_contrasena", Types.VARCHAR));

        this.leerProfesorCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("leer_profesor")
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC),
                        new SqlOutParameter("p_resultado", OracleTypes.CURSOR, new ProfesorRowMapper()));

        this.leerTodosProfesoresCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("leer_todos_profesores")
                .declareParameters(
                        new SqlOutParameter("p_resultado", OracleTypes.CURSOR, new ProfesorRowMapper()));

        this.actualizarProfesorCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("actualizar_profesor")
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC),
                        new SqlParameter("p_identificacion", Types.VARCHAR),
                        new SqlParameter("p_nombre", Types.VARCHAR),
                        new SqlParameter("p_apellido", Types.VARCHAR),
                        new SqlParameter("p_correo", Types.VARCHAR),
                        new SqlParameter("p_carrera", Types.VARCHAR),
                        new SqlParameter("p_contrasena", Types.VARCHAR));

        this.eliminarProfesorCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("eliminar_profesor")
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC));
    }

    public void crearProfesor(Profesor profesor) {
        crearProfesorCall.execute(profesor.getId(), profesor.getIdentificacion(), profesor.getNombre(), profesor.getApellido(), profesor.getCorreo(), profesor.getCarrera(), profesor.getContrasena());
    }

    public Profesor leerProfesor(Long id) {
        Map<String, Object> result = leerProfesorCall.execute(id);
        return ((List<Profesor>) result.get("p_resultado")).get(0);
    }

    public List<Profesor> listarProfesores() {
        Map<String, Object> result = leerTodosProfesoresCall.execute();
        return (List<Profesor>) result.get("p_resultado");
    }

    public void actualizarProfesor(Profesor profesor) {
        actualizarProfesorCall.execute(profesor.getId(), profesor.getIdentificacion(), profesor.getNombre(), profesor.getApellido(), profesor.getCorreo(), profesor.getCarrera(), profesor.getContrasena());
    }

    public void eliminarProfesor(Long id) {
        eliminarProfesorCall.execute(id);
    }

    private static final class ProfesorRowMapper implements RowMapper<Profesor> {
        @Override
        public Profesor mapRow(ResultSet rs, int rowNum) throws SQLException {
            Profesor profesor = new Profesor();
            profesor.setId(rs.getLong("id"));
            profesor.setIdentificacion(rs.getString("identificacion"));
            profesor.setNombre(rs.getString("nombre"));
            profesor.setApellido(rs.getString("apellido"));
            profesor.setCorreo(rs.getString("correo"));
            profesor.setCarrera(rs.getString("carrera"));
            profesor.setContrasena(rs.getString("contrasena"));
            return profesor;
        }
    }
}
