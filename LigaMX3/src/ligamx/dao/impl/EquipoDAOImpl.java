package ligamx.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ligamx.dao.EquipoDAO;
import ligamx.dto.EquipoDTO;
import ligamx.util.ConexionBD;
import ligamx.util.ExcepcionAplicacion;

/**
 *
 * @author hector.lopez
 */
public class EquipoDAOImpl extends ConexionBD implements EquipoDAO {

    private static final String TABLA_EQUIPO = "EQUIPOC18";

    @Override
    public EquipoDTO buscarEquipoporId(Integer id) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a buscar Equipo por id");
        }
        try {
            Map<Integer, Object> params = new HashMap<>();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT NOMBRE FROM " + TABLA_EQUIPO + " WHERE IDEQUIPO = ?");
            setSql(sql);
            params.put(1, id);
            ejecutarQuery(params);
            EquipoDTO equipo = new EquipoDTO();
            while (getRset().next()) {
                equipo.setIdEquipo(id);
                equipo.setNombre(getRset().getString(1));
            }
            cerrarConexion();
            return equipo;
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    @Override
    public EquipoDTO buscarEquipoporNombre(String nombre) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a buscar Equipo por nombre");
        }
        try {
            Map<Integer, Object> params = new HashMap<>();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT IDEQUIPO FROM " + TABLA_EQUIPO + " WHERE NOMBRE = ?");
            setSql(sql);
            params.put(1, nombre);
            ejecutarQuery(params);
            EquipoDTO equipo = new EquipoDTO();
            while (getRset().next()) {
                equipo.setIdEquipo(getRset().getInt(1));
                equipo.setNombre(nombre);
            }
            cerrarConexion();
            return equipo;
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    @Override
    public List<EquipoDTO> buscarEquipos() throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a buscar Equipos");
        }
        try {
            Map params = new HashMap();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT IDEQUIPO, NOMBRE FROM " + TABLA_EQUIPO + "");
            setSql(sql);
            ejecutarQuery(params);
            List<EquipoDTO> equipoList = new ArrayList<>();
            while (getRset().next()) {
                EquipoDTO equipo = new EquipoDTO();
                equipo.setIdEquipo(getRset().getInt(1));
                equipo.setNombre(getRset().getString(2));
                equipoList.add(equipo);
            }
            cerrarConexion();
            return equipoList;
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    @Override
    public boolean insertarEquipo(EquipoDTO equipo) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a insertar Equipo");
        }
        Map<Integer, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO " + TABLA_EQUIPO + " (NOMBRE) VALUES (?)");
        setSql(sql);
        params.put(1, equipo.getNombre());
        return ejecutarUpdate(params);
    }
}
