package uniquindio.proyectoBD2.entity;

public class Pregunta {

    private Long id;
    private String pregunta;
    private String opcion1;
    private String opcion2;
    private String opcion3;
    private String opcion4;
    private String opcionCorrecta;
    private Long tipoPreguntaId;
    private String tipoPreguntaNombre;
    private Long estadisticaPreguntaId;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public String getOpcion4() {
        return opcion4;
    }

    public void setOpcion4(String opcion4) {
        this.opcion4 = opcion4;
    }

    public String getOpcionCorrecta() {
        return opcionCorrecta;
    }

    public void setOpcionCorrecta(String opcionCorrecta) {
        this.opcionCorrecta = opcionCorrecta;
    }

    public Long getTipoPreguntaId() {
        return tipoPreguntaId;
    }

    public void setTipoPreguntaId(Long tipoPreguntaId) {
        this.tipoPreguntaId = tipoPreguntaId;
    }

    public String getTipoPreguntaNombre() {
        return tipoPreguntaNombre;
    }

    public void setTipoPreguntaNombre(String tipoPreguntaNombre) {
        this.tipoPreguntaNombre = tipoPreguntaNombre;
    }

    public Long getEstadisticaPreguntaId() {
        return estadisticaPreguntaId;
    }

    public void setEstadisticaPreguntaId(Long estadisticaPreguntaId) {
        this.estadisticaPreguntaId = estadisticaPreguntaId;
    }
}
