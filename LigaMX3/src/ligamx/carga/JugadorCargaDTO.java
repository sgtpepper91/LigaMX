package ligamx.carga;

/**
 *
 * @author hector.lopez
 */
public class JugadorCargaDTO extends BaseCargaDTO {

    private String nombre;
    private String equipo;
    private Integer numero;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "JugadorCargaDTO{" + "nombre=" + nombre + ", equipo=" + equipo + ", numero=" + numero + '}';
    }
}
