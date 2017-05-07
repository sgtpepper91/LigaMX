
package ligamx.service.impl;

import java.util.Map;
import ligamx.dao.CocienteDAO;
import ligamx.dao.impl.CocienteDAOImpl;
import ligamx.dto.CocienteDTO;
import ligamx.dto.EquipoDTO;
import ligamx.service.CocienteService;
import ligamx.util.BaseGeneral;
import ligamx.util.ExcepcionAplicacion;


public class CocienteServiceImpl extends BaseGeneral implements CocienteService {
    private final CocienteDAO cocienteDAO;

    public CocienteServiceImpl() {
        cocienteDAO = new CocienteDAOImpl();
    }

    @Override
    public boolean insertarCociente(EquipoDTO equipo) throws ExcepcionAplicacion {
        return cocienteDAO.insertarCociente(equipo);
    }

    @Override
    public boolean actualizarCociente(EquipoDTO equipo) throws ExcepcionAplicacion {
       return cocienteDAO.actualizarCociente(equipo);
    }

    @Override
    public Map<String, CocienteDTO> obtenerCociente() throws ExcepcionAplicacion {
        return cocienteDAO.obtenerCociente();
    }

}
