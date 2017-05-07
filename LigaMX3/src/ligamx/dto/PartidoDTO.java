package ligamx.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import ligamx.util.BaseDTO;

/**
 *
 * @author hector.lopez
 */
public class PartidoDTO extends BaseDTO implements Serializable {

    private Integer idPartido;

    private Integer jornada;

    private Integer idLocal;

    private Integer ml;

    private Integer idVisitante;

    private Integer mv;

    private List<GolDTO> goles;
    
    private Date fecha;
    
    public Integer getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Integer idPartido) {
        this.idPartido = idPartido;
    }

    public Integer getJornada() {
        return jornada;
    }

    public void setJornada(Integer jornada) {
        this.jornada = jornada;
    }

    public Integer getMl() {
        return ml;
    }

    public void setMl(Integer ml) {
        this.ml = ml;
    }

    public Integer getMv() {
        return mv;
    }

    public void setMv(Integer mv) {
        this.mv = mv;
    }

    public List<GolDTO> getGoles() {
        return goles;
    }

    public void setGoles(List<GolDTO> goles) {
        this.goles = goles;
    }

    public Integer getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Integer idLocal) {
        this.idLocal = idLocal;
    }

    public Integer getIdVisitante() {
        return idVisitante;
    }

    public void setIdVisitante(Integer idVisitante) {
        this.idVisitante = idVisitante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
