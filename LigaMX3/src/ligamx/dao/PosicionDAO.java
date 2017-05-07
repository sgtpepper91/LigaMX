package ligamx.dao;

import java.util.Map;
import ligamx.dto.EquipoDTO;
import ligamx.dto.PosicionDTO;
import ligamx.util.ExcepcionAplicacion;

/**
 *
 * @author hecto
 */
public interface PosicionDAO {

    /**
     * @param equipo
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    boolean insertarPosicion(EquipoDTO equipo) throws ExcepcionAplicacion;

    /**
     * @param equipo
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    boolean actualizarPosicion(EquipoDTO equipo) throws ExcepcionAplicacion;

    /**
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    Map<String,PosicionDTO> obtenerPosicion() throws ExcepcionAplicacion;
}
