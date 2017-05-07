package ligamx.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import ligamx.dao.CocienteDAO;
import ligamx.dto.CocienteDTO;
import ligamx.dto.EquipoDTO;
import ligamx.util.ConexionBD;
import ligamx.util.ExcepcionAplicacion;

public class CocienteDAOImpl extends ConexionBD implements CocienteDAO {

    @Override
    public boolean insertarCociente(EquipoDTO equipo) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a insertar Cociente");
        }
        Map<Integer, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO COCIENTEC17(IDEQUIPO, JA14, JC15, JA15, JC16, ");
        sql.append("JA16, JC17, TJ, PA14, DGA14, PC15, DGC15, PA15, DGA15, PC16, ");
        sql.append("DGC16, PA16, DGA16, PC17, DGC17, TP, TDG, COCIENTE) ");
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
        sql.append("UPDATE COCIENTEC17 SET JC17 = ?, TJ = ?, PC17 = ?, DGC17 =?, ");
        sql.append("TP = ?, TDG = ?, COCIENTE = ? WHERE IDEQUIPO = ?");
        setSql(sql);
        params.put(1, equipo.getCociente().getJc17());
        params.put(2, equipo.getCociente().getTj());
        params.put(3, equipo.getCociente().getPc17());
        params.put(4, equipo.getCociente().getDgc17());
        params.put(5, equipo.getCociente().getTp());
        params.put(6, equipo.getCociente().getTdg());
        params.put(7, equipo.getCociente().getCociente());
        params.put(8, equipo.getIdEquipo());
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
            sql.append("SELECT E.NOMBRE, C.JA14, C.JC15, C.JA15, C.JC16, ");
            sql.append("C.JA16, C.JC17, C.TJ, C.PA14, C.DGA14, C.PC15, C.DGC15, C.PA15, C.DGA15, C.PC16, ");
            sql.append("C.DGC16, C.PA16, C.DGA16, C.PC17, C.DGC17, C.TP, C.TDG, C.COCIENTE ");
            sql.append("FROM COCIENTEC17 C INNER JOIN EQUIPO E ");
            sql.append("ON C.IDEQUIPO = E.IDEQUIPO");
            setSql(sql);
            ejecutarQuery(params);
            Map<String, CocienteDTO> cocienteMap = new HashMap<>();
            while (getRset().next()) {
                CocienteDTO cociente = new CocienteDTO();
                cociente.setJa14(getRset().getInt(2));
                cociente.setJc15(getRset().getInt(3));
                cociente.setJa15(getRset().getInt(4));
                cociente.setJc16(getRset().getInt(5));
                cociente.setJa16(getRset().getInt(6));
                cociente.setJc17(getRset().getInt(7));
                cociente.setTj(getRset().getInt(8));
                cociente.setPa14(getRset().getInt(9));
                cociente.setDga14(getRset().getInt(10));
                cociente.setPc15(getRset().getInt(11));
                cociente.setDgc15(getRset().getInt(12));
                cociente.setPa15(getRset().getInt(13));
                cociente.setDga15(getRset().getInt(14));
                cociente.setPc16(getRset().getInt(15));
                cociente.setDgc16(getRset().getInt(16));
                cociente.setPa16(getRset().getInt(17));
                cociente.setDga16(getRset().getInt(18));
                cociente.setPc17(getRset().getInt(19));
                cociente.setDgc17(getRset().getInt(20));
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
