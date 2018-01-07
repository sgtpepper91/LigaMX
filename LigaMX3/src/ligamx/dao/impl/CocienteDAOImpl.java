package ligamx.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import ligamx.dao.CocienteDAO;
import ligamx.dto.CocienteDTO;
import ligamx.dto.EquipoDTO;
import ligamx.util.ConexionBD;
import ligamx.util.Constantes;
import ligamx.util.ExcepcionAplicacion;

public class CocienteDAOImpl extends ConexionBD implements CocienteDAO {


    @Override
    public boolean insertarCociente(EquipoDTO equipo) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a insertar Cociente");
        }
        Map<Integer, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO " + Constantes.TABLA_COCIENTE + "(IDEQUIPO, JA15, JC16, JA16, JC17, ");
        sql.append("JA17, JC18, TJ, PA15, DGA15, PC16, DGC16, PA16, DGA16, PC17, ");
        sql.append("DGC17, PA17, DGA17, PC18, DGC18, TP, TDG, COCIENTE) ");
        sql.append("VALUES (?, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)");
        setSql(sql);
        params.put(1, equipo.getIdEquipo());
        return ejecutarUpdate(params);
    }

    @Override
    public boolean actualizarCociente(EquipoDTO equipo) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a actualizar Cociente");
        }
        Map<Integer, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE " + Constantes.TABLA_COCIENTE + " SET JA15 = ?, JC16 = ?, JA16 = ?, JC17 = ?, JA17 = ?, JC18 = ?, TJ = ?,"
                + " PA15 = ?, PC16 = ?, PA16 = ?, PC17 = ?, PA17 = ?, PC18 = ?, TP = ?, DGA15 = ?, DGC16 = ?, DGA16 = ?, DGC17 = ?, "
                + "DGA17 = ?, DGC18 = ?, TDG = ?, COCIENTE = ?  WHERE IDEQUIPO = ?");
        setSql(sql);
        params.put(1, equipo.getCociente().getJa15());
        params.put(2, equipo.getCociente().getJc16());
        params.put(3, equipo.getCociente().getJa16());
        params.put(4, equipo.getCociente().getJc17());
        params.put(5, equipo.getCociente().getJa17());
        params.put(6, equipo.getCociente().getJc18());
        params.put(7, equipo.getCociente().getTj());
        params.put(8, equipo.getCociente().getPa15());
        params.put(9, equipo.getCociente().getPc16());
        params.put(10, equipo.getCociente().getPa16());
        params.put(11, equipo.getCociente().getPc17());
        params.put(12, equipo.getCociente().getPa17());
        params.put(13, equipo.getCociente().getPc18());
        params.put(14, equipo.getCociente().getTp());
        params.put(15, equipo.getCociente().getDga15());
        params.put(16, equipo.getCociente().getDgc16());
        params.put(17, equipo.getCociente().getDga16());
        params.put(18, equipo.getCociente().getDgc17());
        params.put(19, equipo.getCociente().getDga17());
        params.put(20, equipo.getCociente().getDgc18());
        params.put(21, equipo.getCociente().getTdg());
        params.put(22, equipo.getCociente().getCociente());
        params.put(23, equipo.getIdEquipo());
        return ejecutarUpdate(params);
    }

    @Override
    public Map<String, CocienteDTO> obtenerCociente() throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a obtener Cociente");
        }
        try {
            Map params = new HashMap();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT E.NOMBRE, C.JA15, C.JC16, C.JA16, C.JC17, C.JA17, C.JC18, ");
            sql.append("C.TJ, C.PA15, C.DGA15, C.PC16, C.DGC16, C.PA16, C.DGA16, C.PC17, ");
            sql.append("C.DGC17, C.PA17, C.DGA17, C.PC18, C.DGC18, C.TP, C.TDG, C.COCIENTE ");
            sql.append("FROM " + Constantes.TABLA_COCIENTE + " C INNER JOIN " + Constantes.TABLA_EQUIPO + " E ");
            sql.append("ON C.IDEQUIPO = E.IDEQUIPO");
            setSql(sql);
            ejecutarQuery(params);
            Map<String, CocienteDTO> cocienteMap = new HashMap<>();
            while (getRset().next()) {
                CocienteDTO cociente = new CocienteDTO();
                cociente.setJa15(getRset().getInt(2));
                cociente.setJc16(getRset().getInt(3));
                cociente.setJa16(getRset().getInt(4));
                cociente.setJc17(getRset().getInt(5));
                cociente.setJa17(getRset().getInt(6));
                cociente.setJc18(getRset().getInt(7));
                cociente.setTj(getRset().getInt(8));
                cociente.setPa15(getRset().getInt(9));
                cociente.setDga15(getRset().getInt(10));
                cociente.setPc16(getRset().getInt(11));
                cociente.setDgc16(getRset().getInt(12));
                cociente.setPa16(getRset().getInt(13));
                cociente.setDga16(getRset().getInt(14));
                cociente.setPc17(getRset().getInt(15));
                cociente.setDgc17(getRset().getInt(16));
                cociente.setPa17(getRset().getInt(17));
                cociente.setDga17(getRset().getInt(18));
                cociente.setPc18(getRset().getInt(19));
                cociente.setDgc18(getRset().getInt(20));
                cociente.setTp(getRset().getInt(21));
                cociente.setTdg(getRset().getInt(22));
                cociente.setCociente(getRset().getDouble(23));
                cocienteMap.put(getRset().getString(1), cociente);
            }
            cerrarConexion();
            return cocienteMap;
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }
}
