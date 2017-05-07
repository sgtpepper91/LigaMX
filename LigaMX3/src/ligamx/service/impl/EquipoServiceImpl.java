package ligamx.service.impl;

import java.util.List;
import ligamx.dao.EquipoDAO;
import ligamx.dao.impl.EquipoDAOImpl;
import ligamx.dto.EquipoDTO;
import ligamx.dto.PartidoDTO;
import ligamx.dto.PosicionDTO;
import ligamx.service.CocienteService;
import ligamx.service.EquipoService;
import ligamx.service.PartidoService;
import ligamx.service.PosicionService;
import ligamx.util.BaseGeneral;
import ligamx.util.ExcepcionAplicacion;

public class EquipoServiceImpl extends BaseGeneral implements EquipoService {

    private final EquipoDAO equipoDAO;
    private final CocienteService cocienteService;
    private final PosicionService posicionService;
    private final PartidoService partidoService;

    public EquipoServiceImpl() {
        equipoDAO = new EquipoDAOImpl();
        cocienteService = new CocienteServiceImpl();
        posicionService = new PosicionServiceImpl();
        partidoService = new PartidoServiceImpl();
    }

    @Override
    public boolean insertarEquipo(EquipoDTO equipo) throws ExcepcionAplicacion {
        if (equipoDAO.insertarEquipo(equipo)) {
            if (cocienteService.insertarCociente(equipo)) {
                return posicionService.insertarPosicion(equipo);
            }
            return false;
        }
        return false;
    }

    @Override
    public List<EquipoDTO> buscarEquipos() throws ExcepcionAplicacion {
        return equipoDAO.buscarEquipos();
    }

    @Override
    public EquipoDTO buscarEquipoporNombre(String nombre) throws ExcepcionAplicacion {
        return equipoDAO.buscarEquipoporNombre(nombre);
    }

    @Override
    public EquipoDTO buscarEquipoporId(Integer id) throws ExcepcionAplicacion {
        return equipoDAO.buscarEquipoporId(id);
    }

    @Override
    public boolean actualizarEquipo(Integer idPartido) throws ExcepcionAplicacion {
        LOGGER.info("Entra a método para actualizar equipo");
        PartidoDTO partido = partidoService.buscarPartidosporId(idPartido);
        EquipoDTO local = buscarEquipoporId(partido.getIdLocal());
        EquipoDTO visitante = buscarEquipoporId(partido.getIdVisitante());
        List<PartidoDTO> partidosLocal = partidoService.buscarPartidosporEquipo(local);
        List<PartidoDTO> partidosVisitante = partidoService.buscarPartidosporEquipo(visitante);
        calcularDatosEquipo(local, partidosLocal);
        posicionService.actualizarPosicion(local);
        cocienteService.actualizarCociente(local);
        calcularDatosEquipo(visitante, partidosVisitante);
        return posicionService.actualizarPosicion(visitante) && cocienteService.actualizarCociente(visitante);
    }

    private void calcularDatosEquipo(EquipoDTO equipo, List<PartidoDTO> partidos) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a calcular datos");
        }
        Integer jj = 0;
        Integer jg = 0;
        Integer je = 0;
        Integer jp = 0;
        Integer gf = 0;
        Integer gc = 0;
        for (PartidoDTO partido : partidos) {
            if (null != partido.getMl() && null != partido.getMv()) {
                jj++;
                if (isLocal(equipo, partido)) {
                    if (partido.getMl() > partido.getMv()) {
                        jg++;
                    } else if (partido.getMl() < partido.getMv()) {
                        jp++;
                    } else {
                        je++;
                    }
                    gf += partido.getMl();
                    gc += partido.getMv();
                } else {
                    if (partido.getMl() < partido.getMv()) {
                        jg++;
                    } else if (partido.getMl() > partido.getMv()) {
                        jp++;
                    } else {
                        je++;
                    }
                    gf += partido.getMv();
                    gc += partido.getMl();
                }
            }
        }
        equipo.setPosicion(new PosicionDTO());
        equipo.setCociente(cocienteService.obtenerCociente().get(equipo.getNombre()));
        equipo.getPosicion().setJj(jj);
        equipo.getPosicion().setJg(jg);
        equipo.getPosicion().setJe(je);
        equipo.getPosicion().setJp(jp);
        equipo.getPosicion().setGf(gf);
        equipo.getPosicion().setGc(gc);
        equipo.getPosicion().setDg(gf - gc);
        equipo.getPosicion().setPts(jg * 3 + je);
        equipo.getCociente().setJc17(jj);
        equipo.getCociente().setTj(equipo.getCociente().getJa14() + equipo.getCociente().getJc15()
                + equipo.getCociente().getJa15() + equipo.getCociente().getJc16()
                + equipo.getCociente().getJa16() + equipo.getCociente().getJc17());
        equipo.getCociente().setPc17(equipo.getPosicion().getPts());
        equipo.getCociente().setDgc17(equipo.getPosicion().getDg());
        equipo.getCociente().setTp(equipo.getCociente().getPa14() + equipo.getCociente().getPc15()
                + equipo.getCociente().getPa15() + equipo.getCociente().getPc16()
                + equipo.getCociente().getPa16() + equipo.getCociente().getPc17());
        equipo.getCociente().setTdg(equipo.getCociente().getDga14() + equipo.getCociente().getDgc15()
                + equipo.getCociente().getDga15() + equipo.getCociente().getDgc16()
                + equipo.getCociente().getDga16() + equipo.getCociente().getDgc17());
        equipo.getCociente().setCociente(equipo.getCociente().getTp().doubleValue() / equipo.getCociente().getTj().doubleValue());
    }

    private boolean isLocal(EquipoDTO equipo, PartidoDTO partido) {
        return equipo.getIdEquipo().equals(partido.getIdLocal());
    }

}
