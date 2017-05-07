package ligamx.util;

/**
 *
 * @author hecto
 */
public enum EquiposEnum {
    AMERICA(1, "América"),
    ATLAS(2, "Atlas"),
    CRUZAZUL(3, "Cruz Azul"),
    GUADALAJARA(4, "Guadalajara"),
    JAGUARES(5, "Jaguares"),
    LEON(6, "León"),
    MONTERREY(7, "Monterrey"),
    MORELIA(8, "Morelia"),
    NECAXA(9, "Necaxa"),
    PACHUCA(10, "Pachuca"),
    PUEBLA(11, "Puebla"),
    QUERETARO(12, "Querétaro"),
    SANTOS(13, "Santos"),
    TIJUANA(14, "Tijuana"),
    TOLUCA(15, "Toluca"),
    UANL(16, "UANL"),
    UNAM(17, "UNAM"),
    VERACRUZ(18, "Veracruz");
    
    private final Integer id;
    private final String nombre;

    private EquiposEnum(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    
}
