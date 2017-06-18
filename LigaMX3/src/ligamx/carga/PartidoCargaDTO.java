package ligamx.carga;

import java.util.Date;

/**
 *
 * @author hector.lopez
 */
public class PartidoCargaDTO extends BaseCargaDTO {

    private Integer jornada;
    private String local;
    private String visitante;
    private Date fecha;

    public Integer getJornada() {
        return jornada;
    }

    public void setJornada(Integer jornada) {
        this.jornada = jornada;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
