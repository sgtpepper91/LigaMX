package ligamx.dao;

import java.util.List;
import ligamx.dto.EquipoDTO;
import ligamx.util.ExcepcionAplicacion;

/**
 *
 * @author hector.lopez
 */
public interface EquipoDAO {

    /**
     * @param id
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    EquipoDTO buscarEquipoporId(Integer id) throws ExcepcionAplicacion;

    /**
     * @param nombre
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    EquipoDTO buscarEquipoporNombre(String nombre)throws ExcepcionAplicacion;

    /**
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    List<EquipoDTO> buscarEquipos()throws ExcepcionAplicacion;

    /**
     * @param equipo
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    boolean insertarEquipo(EquipoDTO equipo)throws ExcepcionAplicacion;
    
}
