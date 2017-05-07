package ligamx.service.impl;

import java.util.List;
import ligamx.dao.PartidoDAO;
import ligamx.dao.impl.PartidoDAOImpl;
import ligamx.dto.EquipoDTO;
import ligamx.dto.PartidoDTO;
import ligamx.service.PartidoService;
import ligamx.util.BaseGeneral;
import ligamx.util.ExcepcionAplicacion;

public class PartidoServiceImpl extends BaseGeneral implements PartidoService {

    private final PartidoDAO partidoDAO;

    public PartidoServiceImpl() {
        partidoDAO = new PartidoDAOImpl();
    }

    @Override
    public boolean insertarPartido(PartidoDTO partido) throws ExcepcionAplicacion {
        return partidoDAO.insertarPartido(partido);
    }

    @Override
    public boolean actualizarPartido(PartidoDTO partido) throws ExcepcionAplicacion {
        return partidoDAO.actualizarPartido(partido);
    }

    @Override
    public List<PartidoDTO> buscarPartidosporEquipo(EquipoDTO equipo) throws ExcepcionAplicacion {
        return partidoDAO.buscarPartidosporEquipo(equipo);
    }

    @Override
    public List<PartidoDTO> buscarPartidosporJornada(Integer jornada) throws ExcepcionAplicacion {
        return partidoDAO.buscarPartidosporJornada(jornada);
    }

    @Override
    public PartidoDTO buscarPartidosporId(Integer id) throws ExcepcionAplicacion {
        return partidoDAO.buscarPartidosporId(id);
    }

    @Override
    public PartidoDTO buscarPartidoporJornadayLocal(Integer jornada, EquipoDTO equipo) throws ExcepcionAplicacion {
        return partidoDAO.buscarPartidoporJornadayLocal(jornada, equipo);
    }

    @Override
    public PartidoDTO buscarPartidoporEquipos(Integer id1, Integer id2) throws ExcepcionAplicacion {
        return partidoDAO.buscarPartidoporEquipos(id1, id2);
    }

}
