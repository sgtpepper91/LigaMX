package ligamx.dao;

import java.util.List;
import ligamx.dto.GolDTO;
import ligamx.dto.GoleadorDTO;
import ligamx.dto.JugadorDTO;
import ligamx.dto.PartidoDTO;
import ligamx.util.ExcepcionAplicacion;

/**
 *
 * @author hecto
 */
public interface GolDAO {

    /**
     * @param gol
     * @param partido
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    boolean insertarGol(GolDTO gol, PartidoDTO partido) throws ExcepcionAplicacion;

    /**
     * @param gol
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    boolean actualizarGol(GolDTO gol) throws ExcepcionAplicacion;

    /**
     * @param partido
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    List<GolDTO> buscarGolesporPartido(PartidoDTO partido) throws ExcepcionAplicacion;

    /**
     * @param jugador
     * @return
     * @throws ligamx.util.ExcepcionAplicacion
     */
    Integer buscarGolesporJugador(JugadorDTO jugador) throws ExcepcionAplicacion;
    
    /**
     * 
     * @return
     * @throws ExcepcionAplicacion 
     */
    List<GoleadorDTO> buscarGoleadores() throws ExcepcionAplicacion;
}
