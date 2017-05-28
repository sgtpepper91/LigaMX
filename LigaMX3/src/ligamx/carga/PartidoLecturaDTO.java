package ligamx.carga;

/**
 *
 * @author hector.lopez
 */
public class PartidoLecturaDTO extends BaseLecturaDTO {

    public PartidoLecturaDTO() {
        super.header = new String[]{"Jornada, Local, Visitante, Fecha"};
        super.mapper = new String[]{"jornada, local, visitante, fecha"};
    }

    @NotNullAndEmpty(mensaje = "La jornada es un campo requerido.")
    @Size(size = 2, mensaje = "La jornada no puede tener más de 2 caracteres.")
    @Type(tipo = "Integer", mensaje = "La jornada debe ser un número natural.")
    private String jornada;

    @NotNullAndEmpty(mensaje = "El equipo local es un campo requerido.")
    @Size(size = 50, mensaje = "El equipo local no puede tener más de 50 caracteres.")
    private String local;

    @NotNullAndEmpty(mensaje = "El equipo visitante es un campo requerido.")
    @Size(size = 50, mensaje = "El equipo visitante no puede tener más de 50 caracteres.")
    private String visitante;

    @NotNullAndEmpty(mensaje = "El equipo visitante es un campo requerido.")
    @Size(size = 16, mensaje = "El equipo visitante no puede tener más de 16 caracteres.")
    @Pattern(patron = "(?:((0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19|20)\\d\\d)) (([01][0-9]|2[0-3]):)([0-5][0-9])", mensaje = "La fecha no cumple con el formato dd/MM/yyy HH:mm")
    private String fecha;

    @AssertTrue(mensaje = "La jornada no puede ser mayor a 18")
    public boolean isJornadaValida() {
        return Integer.parseInt(this.jornada) > 18;
    }

}
