package ligamx.service;

import java.util.List;
import ligamx.dto.EquipoDTO;
import ligamx.dto.GolDTO;
import ligamx.util.ExcepcionAplicacion;

/**
 *
 * @author hecto
 */
public interface EquipoService {

    /**
     * @param equipo
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    boolean insertarEquipo(EquipoDTO equipo) throws ExcepcionAplicacion;

    /**
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    List<EquipoDTO> buscarEquipos() throws ExcepcionAplicacion;

    /**
     * @param nombre
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    EquipoDTO buscarEquipoporNombre(String nombre) throws ExcepcionAplicacion;

    /**
     * @param id
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    EquipoDTO buscarEquipoporId(Integer id) throws ExcepcionAplicacion;
    
    /**
     * 
     * @param gol
     * @param idPartido
     * @return 
     * @throws ligamx.util.ExcepcionAplicacion
     */
    boolean actualizarEquipo(Integer idPartido) throws ExcepcionAplicacion;
}

