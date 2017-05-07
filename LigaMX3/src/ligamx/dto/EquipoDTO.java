package ligamx.dto;

import java.io.Serializable;
import java.util.List;
import ligamx.util.BaseDTO;

/**
 *
 * @author hector.lopez
 */
public class EquipoDTO extends BaseDTO implements Serializable{

    private Integer idEquipo;
    private String nombre;
    private PosicionDTO posicion;
    private CocienteDTO cociente;
    private List<JugadorDTO> jugadorList;

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PosicionDTO getPosicion() {
        return posicion;
    }

    public void setPosicion(PosicionDTO posicion) {
        this.posicion = posicion;
    }

    public CocienteDTO getCociente() {
        return cociente;
    }

    public void setCociente(CocienteDTO cociente) {
        this.cociente = cociente;
    }

    public List<JugadorDTO> getJugadorList() {
        return jugadorList;
    }

    public void setJugadorList(List<JugadorDTO> jugadorList) {
        this.jugadorList = jugadorList;
    }
}
