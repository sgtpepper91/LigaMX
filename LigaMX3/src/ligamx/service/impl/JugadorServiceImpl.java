
package ligamx.service.impl;

import java.util.List;
import ligamx.dao.JugadorDAO;
import ligamx.dao.impl.JugadorDAOImpl;
import ligamx.dto.EquipoDTO;
import ligamx.dto.JugadorDTO;
import ligamx.service.JugadorService;
import ligamx.util.BaseGeneral;
import ligamx.util.ExcepcionAplicacion;


public class JugadorServiceImpl extends BaseGeneral implements JugadorService {
    
    private final JugadorDAO jugadorDAO;

    public JugadorServiceImpl() {
        jugadorDAO = new JugadorDAOImpl();
    }    

    @Override
    public boolean insertarJugador(JugadorDTO jugador, Integer idEquipo) throws ExcepcionAplicacion {
        return jugadorDAO.insertarJugador(jugador, idEquipo);
    }

    @Override
    public boolean modificarJugador(JugadorDTO jugador, Integer idEquipo) throws ExcepcionAplicacion {
        return jugadorDAO.modificarJugador(jugador, idEquipo);
    }

    @Override
    public boolean borrarJugador(JugadorDTO jugador) throws ExcepcionAplicacion {
        return jugadorDAO.borrarJugador(jugador);
    }

    @Override
    public List<JugadorDTO> buscarJugadores(EquipoDTO equipo) throws ExcepcionAplicacion {
        return jugadorDAO.buscarJugadores(equipo);
    }

    @Override
    public JugadorDTO buscarJugadorPorNombre(String jugador) throws ExcepcionAplicacion {
        return jugadorDAO.buscarJugadorPorNombre(jugador);
    }

    @Override
    public JugadorDTO buscarJugadorPorId(Integer id) throws ExcepcionAplicacion {
        return jugadorDAO.buscarJugadorPorId(id);
    }

}
