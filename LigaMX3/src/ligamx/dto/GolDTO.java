package ligamx.dto;

import java.io.Serializable;
import ligamx.util.BaseDTO;

/**
 *
 * @author hector.lopez
 */
public class GolDTO extends BaseDTO implements Serializable {

    private Integer idGol;

    private Integer idJugador;

    private Integer idEquipo;

    private Integer minuto;

    private Character autogol;

    public Integer getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Integer idJugador) {
        this.idJugador = idJugador;
    }

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Integer getMinuto() {
        return minuto;
    }

    public void setMinuto(Integer minuto) {
        this.minuto = minuto;
    }

    public Character getAutogol() {
        return autogol;
    }

    public void setAutogol(Character autogol) {
        this.autogol = autogol;
    }

    public Integer getIdGol() {
        return idGol;
    }

    public void setIdGol(Integer idGol) {
        this.idGol = idGol;
    }    
}
