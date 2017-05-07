package ligamx.dto;

import java.io.Serializable;
import ligamx.util.BaseDTO;

/**
 *
 * @author hector.lopez
 */
public class JugadorDTO extends BaseDTO implements Serializable {

    private Integer idJugador;

    private String nombre;

    private Integer numero;
    
    private Integer goles;

    public Integer getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Integer idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getGoles() {
        return goles;
    }

    public void setGoles(Integer goles) {
        this.goles = goles;
    }

}
