package ligamx.dao;

import java.util.List;
import ligamx.dto.EquipoDTO;
import ligamx.dto.PartidoDTO;
import ligamx.util.ExcepcionAplicacion;

/**
 *
 * @author hecto
 */
public interface PartidoDAO {

    /**
     * @param partido
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    boolean insertarPartido(PartidoDTO partido) throws ExcepcionAplicacion;

    /**
     * @param partido
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    boolean actualizarPartido(PartidoDTO partido) throws ExcepcionAplicacion;

    /**
     * @param equipo
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    List<PartidoDTO> buscarPartidosporEquipo(EquipoDTO equipo) throws ExcepcionAplicacion;

    /**
     * @param jornada
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    List<PartidoDTO> buscarPartidosporJornada(Integer jornada) throws ExcepcionAplicacion;
    
    /**
     * 
     * @param id
     * @return 
     * @throws ligamx.util.ExcepcionAplicacion
     */
    PartidoDTO buscarPartidosporId(Integer id) throws ExcepcionAplicacion;

    /**
     * 
     * @param jornada
     * @param equipo
     * @return 
     * @throws ligamx.util.ExcepcionAplicacion 
     */
    PartidoDTO buscarPartidoporJornadayLocal(Integer jornada, EquipoDTO equipo) throws ExcepcionAplicacion;

    /**
     * 
     * @param id1
     * @param id2
     * @return
     * @throws ExcepcionAplicacion 
     */
    PartidoDTO buscarPartidoporEquipos(Integer id1, Integer id2) throws ExcepcionAplicacion;
    
    /**
     * 
     * @param jornada
     * @param equipo
     * @return
     * @throws ExcepcionAplicacion 
     */
    List<PartidoDTO> buscarPartidosGrafica(Integer jornada, EquipoDTO equipo) throws ExcepcionAplicacion;
}
