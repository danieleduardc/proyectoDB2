package uniquindio.proyectoBD2.repository;

import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import uniquindio.proyectoBD2.entity.DetalleExamen;
import uniquindio.proyectoBD2.entity.EstadisticaExamen;
import uniquindio.proyectoBD2.entity.Examen;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository
public class ExamenRepository {

    private final JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall crearExamenCall;
    private SimpleJdbcCall leerExamenCall;
    private SimpleJdbcCall leerTodosExamenesCall;
    private SimpleJdbcCall actualizarExamenCall;
    private SimpleJdbcCall eliminarExamenCall;
    private SimpleJdbcCall obtenerDetalleExamenCall;
    private static SimpleJdbcCall obtenerEstadisticasExamenCall;
    private SimpleJdbcCall obtenerExamenesPorProfesorCall;

    @Autowired
    public ExamenRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;

        this.crearExamenCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("crearExamen")
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC),
                        new SqlParameter("p_categoria_id", Types.NUMERIC),
                        new SqlParameter("p_nombre", Types.VARCHAR),
                        new SqlParameter("p_descripcion", Types.VARCHAR),
                        new SqlParameter("p_cantidad_preguntas", Types.NUMERIC),
                        new SqlParameter("p_code", Types.VARCHAR),
                        new SqlParameter("p_profesor_identificacion", Types.VARCHAR),
                        new SqlParameter("p_profesor_id", Types.NUMERIC),
                        new SqlParameter("p_banco_preguntas_id", Types.NUMERIC),
                        new SqlParameter("p_configuracion_id", Types.NUMERIC));

        this.leerExamenCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("leer_examen")
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC),
                        new SqlOutParameter("p_resultado", OracleTypes.CURSOR, new ExamenRowMapper()));

        this.leerTodosExamenesCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("leer_todos_examenes")
                .declareParameters(
                        new SqlOutParameter("p_resultado", OracleTypes.CURSOR, new ExamenRowMapper()));

        this.actualizarExamenCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("actualizar_examen")
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC),
                        new SqlParameter("p_categoria_id", Types.NUMERIC),
                        new SqlParameter("p_nombre", Types.VARCHAR),
                        new SqlParameter("p_descripcion", Types.VARCHAR),
                        new SqlParameter("p_cantidad_preguntas", Types.NUMERIC),
                        new SqlParameter("p_code", Types.VARCHAR),
                        new SqlParameter("p_profesor_identificacion", Types.VARCHAR),
                        new SqlParameter("p_profesor_id", Types.NUMERIC),
                        new SqlParameter("p_banco_preguntas_id", Types.NUMERIC),
                        new SqlParameter("p_configuracion_id", Types.NUMERIC));

        this.eliminarExamenCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("eliminar_examen")
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC));

        this.obtenerDetalleExamenCall = new SimpleJdbcCall(dataSource)
                .withFunctionName("obtener_detalle_examen")
                .declareParameters(
                        new SqlParameter("p_examen_id", Types.NUMERIC),
                        new SqlOutParameter("v_cursor", OracleTypes.CURSOR, new DetalleExamenRowMapper()));

        this.obtenerEstadisticasExamenCall = new SimpleJdbcCall(dataSource)
                .withFunctionName("obtener_estadisticas_examen")
                .declareParameters(
                        new SqlParameter("p_examenId", Types.NUMERIC),
                        new SqlOutParameter("v_cursor", OracleTypes.CURSOR, new EstadisticaExamenRowMapper()));

        this.obtenerExamenesPorProfesorCall = new SimpleJdbcCall(dataSource)
                .withFunctionName("obtener_examenes_por_profesor")
                .declareParameters(
                        new SqlParameter("p_profesorIdentificacion", Types.VARCHAR),
                        new SqlOutParameter("v_cursor", OracleTypes.CURSOR, new ExamenRowMapper()));
    }

    public void crearExamen(Examen examen) {
        crearExamenCall.execute(examen.getId(), examen.getCategoriaId(), examen.getNombre(), examen.getDescripcion(), examen.getCantidadPreguntas(), examen.getCode(), examen.getProfesorIdentificacion(), examen.getProfesorId(), examen.getBancoPreguntasId(), examen.getConfiguracionId());
    }

    public Examen leerExamen(Long id) {
        Map<String, Object> result = leerExamenCall.execute(id);
        return ((List<Examen>) result.get("p_resultado")).get(0);
    }

    public List<Examen> listarExamenes() {
        Map<String, Object> result = leerTodosExamenesCall.execute();
        return (List<Examen>) result.get("p_resultado");
    }

    public void actualizarExamen(Examen examen) {
        actualizarExamenCall.execute(examen.getId(), examen.getCategoriaId(), examen.getNombre(), examen.getDescripcion(), examen.getCantidadPreguntas(), examen.getCode(), examen.getProfesorIdentificacion(), examen.getProfesorId(), examen.getBancoPreguntasId(), examen.getConfiguracionId());
    }

    public void eliminarExamen(Long id) {
        eliminarExamenCall.execute(id);
    }

    public List<DetalleExamen> obtenerDetalleExamen(Long examenId) {
        Map<String, Object> result = obtenerDetalleExamenCall.execute(examenId);
        return (List<DetalleExamen>) result.get("v_cursor");
    }

    public static List<EstadisticaExamen> obtenerEstadisticasExamen(Long examenId) {
        Map<String, Object> result = obtenerEstadisticasExamenCall.execute(examenId);
        return (List<EstadisticaExamen>) result.get("v_cursor");
    }

    public List<Examen> obtenerExamenesPorProfesor(String profesorIdentificacion) {
        Map<String, Object> result = obtenerExamenesPorProfesorCall.execute(profesorIdentificacion);
        return (List<Examen>) result.get("v_cursor");
    }

    private static final class ExamenRowMapper implements RowMapper<Examen> {
        @Override
        public Examen mapRow(ResultSet rs, int rowNum) throws SQLException {
            Examen examen = new Examen();
            examen.setId(rs.getLong("id"));
            examen.setCategoriaId(rs.getLong("categoria_id"));
            examen.setNombre(rs.getString("nombre"));
            examen.setDescripcion(rs.getString("descripcion"));
            examen.setCantidadPreguntas(rs.getInt("cantidadpreguntas"));
            examen.setCode(rs.getString("code"));
            examen.setProfesorIdentificacion(rs.getString("profesor_identificacion"));
            examen.setProfesorId(rs.getLong("profesor_id"));
            examen.setBancoPreguntasId(rs.getLong("banco_preguntas_id"));
            examen.setConfiguracionId(rs.getLong("configuracion_configuracion_id"));
            return examen;
        }
    }

    private static final class DetalleExamenRowMapper implements RowMapper<DetalleExamen> {
        @Override
        public DetalleExamen mapRow(ResultSet rs, int rowNum) throws SQLException {
            DetalleExamen detalleExamen = new DetalleExamen();
            detalleExamen.setNombreExamen(rs.getString("nombre_examen"));
            detalleExamen.setDescripcion(rs.getString("descripcion"));
            detalleExamen.setPregunta(rs.getString("pregunta"));
            detalleExamen.setOpcion1(rs.getString("opcion1"));
            detalleExamen.setOpcion2(rs.getString("opcion2"));
            detalleExamen.setOpcion3(rs.getString("opcion3"));
            detalleExamen.setOpcion4(rs.getString("opcion4"));
            return detalleExamen;
        }
    }

    private static final class EstadisticaExamenRowMapper implements RowMapper<EstadisticaExamen> {
        @Override
        public EstadisticaExamen mapRow(ResultSet rs, int rowNum) throws SQLException {
            EstadisticaExamen estadisticaExamen = new EstadisticaExamen();
            estadisticaExamen.setPorcentaje(rs.getDouble("porcentaje"));
            estadisticaExamen.setTiempo(rs.getInt("tiempo"));
            return estadisticaExamen;
        }
    }

}
