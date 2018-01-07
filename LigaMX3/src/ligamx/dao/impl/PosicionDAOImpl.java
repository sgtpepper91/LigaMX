package ligamx.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import ligamx.dao.PosicionDAO;
import ligamx.dto.EquipoDTO;
import ligamx.dto.PosicionDTO;
import ligamx.util.ConexionBD;
import ligamx.util.Constantes;
import ligamx.util.ExcepcionAplicacion;

public class PosicionDAOImpl extends ConexionBD implements PosicionDAO {

    @Override
    public boolean insertarPosicion(EquipoDTO equipo) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a insertar Posición");
        }
        Map<Integer, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO " + Constantes.TABLA_GENERAL + "(IDEQUIPO, JJ, JG, JE, JP, GF, GC, DG, PT) ");
        sql.append("VALUES (?, 0, 0, 0, 0, 0, 0, 0, 0)");
        setSql(sql);
        params.put(1, equipo.getIdEquipo());
        return ejecutarUpdate(params);
    }

    @Override
    public boolean actualizarPosicion(EquipoDTO equipo) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a actualizar Posición");
        }
        Map<Integer, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE " + Constantes.TABLA_GENERAL + " SET JJ = ?, JG = ?, JE = ?, JP = ?, GF = ?, GC = ?, DG = ?, PT = ? ");
        sql.append("WHERE IDEQUIPO = ?");
        setSql(sql);
        params.put(1, equipo.getPosicion().getJj());
        params.put(2, equipo.getPosicion().getJg());
        params.put(3, equipo.getPosicion().getJe());
        params.put(4, equipo.getPosicion().getJp());
        params.put(5, equipo.getPosicion().getGf());
        params.put(6, equipo.getPosicion().getGc());
        params.put(7, equipo.getPosicion().getDg());
        params.put(8, equipo.getPosicion().getPts());
        params.put(9, equipo.getIdEquipo());
        return ejecutarUpdate(params);
    }

    @Override
    public Map<String, PosicionDTO> obtenerPosicion() throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a obtener Posición");
        }
        try {
            Map params = new HashMap();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT E.NOMBRE, G.JJ, G.JG, G.JE, G.JP, G.GF, G.GC, G.DG, G.PT ");
            sql.append("FROM " + Constantes.TABLA_GENERAL + " G INNER JOIN " + Constantes.TABLA_EQUIPO + " E ");
            sql.append("ON G.IDEQUIPO = E.IDEQUIPO");
            setSql(sql);
            ejecutarQuery(params);
            Map<String, PosicionDTO> posicionMap = new HashMap<>();
            while (getRset().next()) {
                PosicionDTO posicion = new PosicionDTO();
                posicion.setJj(getRset().getInt(2));
                posicion.setJg(getRset().getInt(3));
                posicion.setJe(getRset().getInt(4));
                posicion.setJp(getRset().getInt(5));
                posicion.setGf(getRset().getInt(6));
                posicion.setGc(getRset().getInt(7));
                posicion.setDg(getRset().getInt(8));
                posicion.setPts(getRset().getInt(9));
                posicionMap.put(getRset().getString(1), posicion);
            }
            cerrarConexion();
            return posicionMap;
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

}
