package uniquindio.proyectoBD2.entity;

public class Examen {

    private Long id;
    private Long categoriaId;
    private String nombre;
    private String descripcion;
    private int cantidadPreguntas;
    private String code;
    private String profesorIdentificacion;
    private Long profesorId;
    private Long bancoPreguntasId;
    private Long configuracionId;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadPreguntas() {
        return cantidadPreguntas;
    }

    public void setCantidadPreguntas(int cantidadPreguntas) {
        this.cantidadPreguntas = cantidadPreguntas;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProfesorIdentificacion() {
        return profesorIdentificacion;
    }

    public void setProfesorIdentificacion(String profesorIdentificacion) {
        this.profesorIdentificacion = profesorIdentificacion;
    }

    public Long getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Long profesorId) {
        this.profesorId = profesorId;
    }

    public Long getBancoPreguntasId() {
        return bancoPreguntasId;
    }

    public void setBancoPreguntasId(Long bancoPreguntasId) {
        this.bancoPreguntasId = bancoPreguntasId;
    }

    public Long getConfiguracionId() {
        return configuracionId;
    }

    public void setConfiguracionId(Long configuracionId) {
        this.configuracionId = configuracionId;
    }
}
