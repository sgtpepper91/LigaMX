package ligamx.service;

import java.util.List;
import ligamx.dto.EquipoDTO;
import ligamx.dto.JugadorDTO;
import ligamx.util.ExcepcionAplicacion;

/**
 *
 * @author hecto
 */
public interface JugadorService {

    /**
     * @param jugador
     * @param idEquipo
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    boolean insertarJugador(JugadorDTO jugador, Integer idEquipo) throws ExcepcionAplicacion;

    /**
     * @param jugador
     * @param idEquipo
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    boolean modificarJugador(JugadorDTO jugador, Integer idEquipo) throws ExcepcionAplicacion;

    /**
     * @param jugador
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    boolean borrarJugador(JugadorDTO jugador) throws ExcepcionAplicacion;

    /**
     * @param equipo
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    List<JugadorDTO> buscarJugadores(EquipoDTO equipo) throws ExcepcionAplicacion;

    /**
     * 
     * @param jugador
     * @return
     * @throws ExcepcionAplicacion 
     */
    JugadorDTO buscarJugadorPorNombre(String jugador) throws ExcepcionAplicacion;
    
    /**
     * 
     * @param id
     * @return
     * @throws ExcepcionAplicacion 
     */
    JugadorDTO buscarJugadorPorId(Integer id) throws ExcepcionAplicacion;
}
