package uniquindio.proyectoBD2.repository;

import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import uniquindio.proyectoBD2.entity.Pregunta;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository
public class PreguntaRepository {

    private final JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall crearPreguntaCall;
    private SimpleJdbcCall leerPreguntaCall;
    private SimpleJdbcCall leerTodasPreguntasCall;
    private SimpleJdbcCall actualizarPreguntaCall;
    private SimpleJdbcCall eliminarPreguntaCall;

    @Autowired
    public PreguntaRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;

        this.crearPreguntaCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("crear_pregunta")
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC),
                        new SqlParameter("p_pregunta", Types.VARCHAR),
                        new SqlParameter("p_opcion1", Types.VARCHAR),
                        new SqlParameter("p_opcion2", Types.VARCHAR),
                        new SqlParameter("p_opcion3", Types.VARCHAR),
                        new SqlParameter("p_opcion4", Types.VARCHAR),
                        new SqlParameter("p_opcioncorrecta", Types.VARCHAR),
                        new SqlParameter("p_tipo_pregunta_id", Types.NUMERIC),
                        new SqlParameter("p_tipo_pregunta_nombre", Types.VARCHAR),
                        new SqlParameter("p_estadistica_pregunta_id", Types.NUMERIC));

        this.leerPreguntaCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("leer_pregunta")
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC),
                        new SqlOutParameter("p_resultado", OracleTypes.CURSOR, new PreguntaRowMapper()));

        this.leerTodasPreguntasCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("leer_todas_preguntas")
                .declareParameters(
                        new SqlOutParameter("p_resultado", OracleTypes.CURSOR, new PreguntaRowMapper()));

        this.actualizarPreguntaCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("actualizar_pregunta")
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC),
                        new SqlParameter("p_pregunta", Types.VARCHAR),
                        new SqlParameter("p_opcion1", Types.VARCHAR),
                        new SqlParameter("p_opcion2", Types.VARCHAR),
                        new SqlParameter("p_opcion3", Types.VARCHAR),
                        new SqlParameter("p_opcion4", Types.VARCHAR),
                        new SqlParameter("p_opcioncorrecta", Types.VARCHAR),
                        new SqlParameter("p_tipo_pregunta_id", Types.NUMERIC),
                        new SqlParameter("p_tipo_pregunta_nombre", Types.VARCHAR),
                        new SqlParameter("p_estadistica_pregunta_id", Types.NUMERIC));

        this.eliminarPreguntaCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("eliminar_pregunta")
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC));
    }

    public void crearPregunta(Pregunta pregunta) {
        crearPreguntaCall.execute(pregunta.getId(), pregunta.getPregunta(), pregunta.getOpcion1(), pregunta.getOpcion2(), pregunta.getOpcion3(), pregunta.getOpcion4(), pregunta.getOpcionCorrecta(), pregunta.getTipoPreguntaId(), pregunta.getTipoPreguntaNombre(), pregunta.getEstadisticaPreguntaId());
    }

    public Pregunta leerPregunta(Long id) {
        Map<String, Object> result = leerPreguntaCall.execute(id);
        return ((List<Pregunta>) result.get("p_resultado")).get(0);
    }

    public List<Pregunta> listarPreguntas() {
        Map<String, Object> result = leerTodasPreguntasCall.execute();
        return (List<Pregunta>) result.get("p_resultado");
    }

    public void actualizarPregunta(Pregunta pregunta) {
        actualizarPreguntaCall.execute(pregunta.getId(), pregunta.getPregunta(), pregunta.getOpcion1(), pregunta.getOpcion2(), pregunta.getOpcion3(), pregunta.getOpcion4(), pregunta.getOpcionCorrecta(), pregunta.getTipoPreguntaId(), pregunta.getTipoPreguntaNombre(), pregunta.getEstadisticaPreguntaId());
    }

    public void eliminarPregunta(Long id) {
        eliminarPreguntaCall.execute(id);
    }

    private static final class PreguntaRowMapper implements RowMapper<Pregunta> {
        @Override
        public Pregunta mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pregunta pregunta = new Pregunta();
            pregunta.setId(rs.getLong("id"));
            pregunta.setPregunta(rs.getString("pregunta"));
            pregunta.setOpcion1(rs.getString("opcion1"));
            pregunta.setOpcion2(rs.getString("opcion2"));
            pregunta.setOpcion3(rs.getString("opcion3"));
            pregunta.setOpcion4(rs.getString("opcion4"));
            pregunta.setOpcionCorrecta(rs.getString("opcioncorrecta"));
            pregunta.setTipoPreguntaId(rs.getLong("tipo_pregunta_id"));
            pregunta.setTipoPreguntaNombre(rs.getString("tipo_pregunta_nombre"));
            pregunta.setEstadisticaPreguntaId(rs.getLong("estadistica_pregunta_id"));
            return pregunta;
        }
    }
}
