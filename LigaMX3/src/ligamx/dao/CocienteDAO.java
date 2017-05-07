package ligamx.dao;

import java.util.Map;
import ligamx.dto.CocienteDTO;
import ligamx.dto.EquipoDTO;
import ligamx.util.ExcepcionAplicacion;

/**
 *
 * @author hecto
 */
public interface CocienteDAO {

    /**
     * @param equipo
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    boolean insertarCociente(EquipoDTO equipo) throws ExcepcionAplicacion;

    /**
     * @param equipo
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    boolean actualizarCociente(EquipoDTO equipo) throws ExcepcionAplicacion;

    /**
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    Map<String, CocienteDTO> obtenerCociente() throws ExcepcionAplicacion;
}
