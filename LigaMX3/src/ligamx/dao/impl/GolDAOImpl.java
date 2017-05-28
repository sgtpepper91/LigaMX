package ligamx.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ligamx.dao.GolDAO;
import ligamx.dto.GolDTO;
import ligamx.dto.GoleadorDTO;
import ligamx.dto.JugadorDTO;
import ligamx.dto.PartidoDTO;
import ligamx.util.ConexionBD;
import ligamx.util.ExcepcionAplicacion;

public class GolDAOImpl extends ConexionBD implements GolDAO {

    private final static String TABLA_GOL = "GOLA17";
    private static final String TABLA_EQUIPO = "EQUIPOC18";

    @Override
    public boolean insertarGol(GolDTO gol, PartidoDTO partido) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a insertar Gol");
        }
        Map<Integer, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO " + TABLA_GOL + "(IDPARTIDO, IDEQUIPO, IDJUGADOR, MINUTO, AUTOGOL) ");
        sql.append("VALUES (?, ?, ?, ?, ?)");
        setSql(sql);
        params.put(1, partido.getIdPartido());
        params.put(2, gol.getIdEquipo());
        params.put(3, gol.getIdJugador());
        params.put(4, gol.getMinuto());
        params.put(5, gol.getAutogol());
        return ejecutarUpdate(params);
    }

    @Override
    public boolean actualizarGol(GolDTO gol) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a actualizar Gol");
        }
        Map<Integer, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE " + TABLA_GOL + " SET IDEQUIPO = ?, IDJUGADOR = ?, MINUTO = ?, AUTOGOL = ? ");
        sql.append("WHERE IDGOL = ?");
        setSql(sql);
        params.put(1, gol.getIdEquipo());
        params.put(2, gol.getIdJugador());
        params.put(3, gol.getMinuto());
        params.put(4, gol.getAutogol());
        params.put(5, gol.getIdGol());
        return ejecutarUpdate(params);
    }

    @Override
    public List<GolDTO> buscarGolesporPartido(PartidoDTO partido) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a buscar Goles por partido");
        }
        try {
            Map<Integer, Object> params = new HashMap<>();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT IDGOL, IDEQUIPO, IDJUGADOR, MINUTO, AUTOGOL ");
            sql.append("FROM " + TABLA_GOL + " WHERE IDPARTIDO = ?");
            setSql(sql);
            params.put(1, partido.getIdPartido());
            ejecutarQuery(params);
            List<GolDTO> golList = new ArrayList<>();
            while (getRset().next()) {
                GolDTO gol = new GolDTO();
                gol.setIdGol(getRset().getInt(1));
                gol.setIdEquipo(getRset().getInt(2));
                gol.setIdJugador(getRset().getInt(3));
                gol.setMinuto(getRset().getInt(4));
                gol.setAutogol(getRset().getString(5).charAt(0));
                golList.add(gol);
            }
            cerrarConexion();
            return golList;
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    @Override
    public Integer buscarGolesporJugador(JugadorDTO jugador) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a buscar Goles por jugador");
        }
        try {
            Map<Integer, Object> params = new HashMap<>();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT COUNT(*) FROM " + TABLA_GOL + " ");
            sql.append("WHERE IDJUGADOR = ? AND AUTOGOL = 'N'");
            setSql(sql);
            params.put(1, jugador.getIdJugador());
            ejecutarQuery(params);
            Integer goles = 0;
            while (getRset().next()) {
                goles = getRset().getInt(1);
            }
            cerrarConexion();
            return goles;
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    @Override
    public List<GoleadorDTO> buscarGoleadores() throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a buscar Goleadores");
        }
        try {
            Map params = new HashMap();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT E.NOMBRE, J.NOMBRE, COUNT(*) AS GOLES ");
            sql.append("FROM " + TABLA_GOL + " G INNER JOIN JUGADOR J ");
            sql.append("ON J.IDJUGADOR = G.IDJUGADOR ");
            sql.append("INNER JOIN " + TABLA_EQUIPO + " E ");
            sql.append("ON E.IDEQUIPO = G.IDEQUIPO ");
            sql.append("GROUP BY E.NOMBRE, J.NOMBRE ORDER BY 3 DESC");
            setSql(sql);
            ejecutarQuery(params);
            List<GoleadorDTO> goleadorList = new ArrayList<>();
            while (getRset().next()) {
                GoleadorDTO goleador = new GoleadorDTO();
                goleador.setEquipo(getRset().getString(1));
                goleador.setNombre(getRset().getString(2));
                goleador.setGoles(getRset().getInt(3));
                goleadorList.add(goleador);
            }
            cerrarConexion();
            return goleadorList;
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

}
