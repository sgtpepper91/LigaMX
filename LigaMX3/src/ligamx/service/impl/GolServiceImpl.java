package ligamx.service.impl;

import java.util.List;
import ligamx.dao.GolDAO;
import ligamx.dao.impl.GolDAOImpl;
import ligamx.dto.GolDTO;
import ligamx.dto.GoleadorDTO;
import ligamx.dto.JugadorDTO;
import ligamx.dto.PartidoDTO;
import ligamx.service.EquipoService;
import ligamx.service.GolService;
import ligamx.service.PartidoService;
import ligamx.util.BaseGeneral;
import ligamx.util.ExcepcionAplicacion;

public class GolServiceImpl extends BaseGeneral implements GolService {
    
    private final GolDAO golDAO;
    private final EquipoService equipoService;
    private final PartidoService partidoService;
    
    public GolServiceImpl() {
        golDAO = new GolDAOImpl();
        equipoService = new EquipoServiceImpl();
        partidoService = new PartidoServiceImpl();
    }
    
    @Override
    public boolean insertarGol(GolDTO gol, PartidoDTO partido) throws ExcepcionAplicacion {
        return golDAO.insertarGol(gol, partido);
    }
    
    @Override
    public boolean actualizarGol(GolDTO gol) throws ExcepcionAplicacion {
        return golDAO.actualizarGol(gol);
    }
    
    @Override
    public List<GolDTO> buscarGolesporPartido(PartidoDTO partido) throws ExcepcionAplicacion {
        return golDAO.buscarGolesporPartido(partido);
    }
    
    @Override
    public Integer buscarGolesporJugador(JugadorDTO jugador) throws ExcepcionAplicacion {
        return golDAO.buscarGolesporJugador(jugador);
    }
    
    @Override
    public List<GoleadorDTO> buscarGoleadores() throws ExcepcionAplicacion {
        return golDAO.buscarGoleadores();
    }
    
}
