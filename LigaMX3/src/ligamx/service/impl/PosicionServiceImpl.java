
package ligamx.service.impl;

import java.util.Map;
import ligamx.dao.PosicionDAO;
import ligamx.dao.impl.PosicionDAOImpl;
import ligamx.dto.EquipoDTO;
import ligamx.dto.PosicionDTO;
import ligamx.service.PosicionService;
import ligamx.util.BaseGeneral;
import ligamx.util.ExcepcionAplicacion;


public class PosicionServiceImpl extends BaseGeneral implements PosicionService {
    
    private final PosicionDAO posicionDAO;

    public PosicionServiceImpl() {
        posicionDAO = new PosicionDAOImpl();
    }

    @Override
    public boolean insertarPosicion(EquipoDTO equipo) throws ExcepcionAplicacion {
        return posicionDAO.insertarPosicion(equipo);
    }

    @Override
    public boolean actualizarPosicion(EquipoDTO equipo) throws ExcepcionAplicacion {
        return posicionDAO.actualizarPosicion(equipo);
    }

    @Override
    public Map<String,PosicionDTO> obtenerPosicion() throws ExcepcionAplicacion {
        return posicionDAO.obtenerPosicion();
    }

}
