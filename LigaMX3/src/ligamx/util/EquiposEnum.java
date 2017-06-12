package ligamx.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hecto
 */
public enum EquiposEnum {
    AMERICA(1, "América"),
    ATLAS(2, "Atlas"),
    CRUZAZUL(3, "Cruz Azul"),
    GUADALAJARA(4, "Guadalajara"),
    LEON(5, "León"),
    LOBOS(6, "Lobos"),
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
    
    public static String[] getArrayEquipos() {
        EquiposEnum[] equiposEnums = EquiposEnum.values();
        List<String> equiposList = new ArrayList<>();
        for (EquiposEnum equipoEnum : equiposEnums) {
            String equipo = equipoEnum.getNombre();
            equiposList.add(equipo);
        }
        return (String[]) equiposList.toArray(new String[0]);
    }
}
