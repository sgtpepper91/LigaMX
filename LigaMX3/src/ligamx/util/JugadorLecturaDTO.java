package ligamx.util;

/**
 *
 * @author hector.lopez
 */
public class JugadorLecturaDTO extends BaseLecturaDTO {

    @NotNullAndEmpty(mensaje = "El nombre es requerido")
    @Size(size = 50, mensaje = "El nombre no puede tener más de 50 caracteres")
    private String nombre;

    @NotNullAndEmpty(mensaje = "El equipo es requerido")
    @Size(size = 50, mensaje = "El equipo no puede tener más de 50 caracteres")
    private String equipo;

    @NotNullAndEmpty(mensaje = "El número es requerido")
    @Size(size = 3, mensaje = "El número no puede tener más de 3 caracteres")
    @Type(tipo = "Integer", mensaje = "El número debe ser un número natural")
    private String numero;

    public JugadorLecturaDTO() {
        super.setHeader(new String[]{"Nombre", "Equipo", "Número"});
        super.setMapper(new String[]{"nombre", "equipo", "numero"});
    }

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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "JugadorLecturaDTO{" + "nombre=" + nombre + ", equipo=" + equipo + ", numero=" + numero + ", mensajes=" + super.getMensajes() + '}';
    }

}
