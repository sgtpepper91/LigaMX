package ligamx.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ligamx.dao.PartidoDAO;
import ligamx.dto.EquipoDTO;
import ligamx.dto.PartidoDTO;
import ligamx.util.ConexionBD;
import ligamx.util.ExcepcionAplicacion;

public class PartidoDAOImpl extends ConexionBD implements PartidoDAO {

    @Override
    public boolean insertarPartido(PartidoDTO partido) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a insertar Partido");
        }
        Map params = new HashMap();
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO PARTIDOSC17(JORNADA, LOCAL, ");
        sql.append("VISITANTE, FECHA) ");
        sql.append("VALUES (?, ?, ?, ?)");
        setSql(sql);
        params.put(1, partido.getJornada());
        params.put(2, partido.getIdLocal());
        params.put(3, partido.getIdVisitante());
        params.put(4, partido.getFecha());
        return ejecutarUpdate(params);
    }

    @Override
    public boolean actualizarPartido(PartidoDTO partido) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a actualizar Partido");
        }
        Map params = new HashMap();
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE PARTIDOSC17 SET ML = ?, MV = ?, FECHA = ? ");
        sql.append("WHERE IDPARTIDO = ?");
        setSql(sql);
        params.put(1, partido.getMl());
        params.put(2, partido.getMv());
        params.put(3, partido.getFecha());
        params.put(4, partido.getIdPartido());
        return ejecutarUpdate(params);
    }

    @Override
    public List<PartidoDTO> buscarPartidosporEquipo(EquipoDTO equipo) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a buscar Partido");
        }
        try {
            Map params = new HashMap();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT IDPARTIDO, JORNADA, LOCAL, ML, VISITANTE, MV, FECHA ");
            sql.append("FROM PARTIDOSC17 WHERE LOCAL = ? OR VISITANTE = ? ORDER BY IDPARTIDO");
            setSql(sql);
            params.put(1, equipo.getIdEquipo());
            params.put(2, equipo.getIdEquipo());
            ejecutarQuery(params);
            List<PartidoDTO> partidoList = new ArrayList<>();
            while (getRset().next()) {
                PartidoDTO partido = new PartidoDTO();
                partido.setIdPartido(getRset().getInt(1));
                partido.setJornada(getRset().getInt(2));
                partido.setIdLocal(getRset().getInt(3));
                partido.setMl(null  == getRset().getObject(4) ? null : getRset().getInt(4));
                partido.setIdVisitante(getRset().getInt(5));
                partido.setMv(null  == getRset().getObject(6) ? null : getRset().getInt(6));
                partido.setFecha(getRset().getTimestamp(7));
                partidoList.add(partido);
            }
            cerrarConexion();
            return partidoList;
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    @Override
    public List<PartidoDTO> buscarPartidosporJornada(Integer jornada) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a buscar Partido por jornada");
        }
        try {
            Map params = new HashMap();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT IDPARTIDO, JORNADA, LOCAL, ML, VISITANTE, MV, FECHA ");
            sql.append("FROM PARTIDOSC17 WHERE JORNADA = ? ORDER BY IDPARTIDO");
            setSql(sql);
            params.put(1, jornada);
            ejecutarQuery(params);
            List<PartidoDTO> partidoList = new ArrayList<>();
            while (getRset().next()) {
                PartidoDTO partido = new PartidoDTO();
                partido.setIdPartido(getRset().getInt(1));
                partido.setJornada(getRset().getInt(2));
                partido.setIdLocal(getRset().getInt(3));
                partido.setMl(null  == getRset().getObject(4) ? null : getRset().getInt(4));
                partido.setIdVisitante(getRset().getInt(5));
                partido.setMv(null  == getRset().getObject(6) ? null : getRset().getInt(6));
                partido.setFecha(getRset().getTimestamp(7));
                partidoList.add(partido);
            }
            cerrarConexion();
            return partidoList;
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    @Override
    public PartidoDTO buscarPartidosporId(Integer id) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a buscar Partidos por id");
        }
        try {
            Map params = new HashMap();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT IDPARTIDO, JORNADA, LOCAL, ML, VISITANTE, MV, FECHA ");
            sql.append("FROM PARTIDOSC17 WHERE IDPARTIDO = ?");
            setSql(sql);
            params.put(1, id);
            ejecutarQuery(params);
            PartidoDTO partido = new PartidoDTO();
            while (getRset().next()) {
                partido.setIdPartido(getRset().getInt(1));
                partido.setJornada(getRset().getInt(2));
                partido.setIdLocal(getRset().getInt(3));
                partido.setMl(null  == getRset().getObject(4) ? null : getRset().getInt(4));
                partido.setIdVisitante(getRset().getInt(5));
                partido.setMv(null  == getRset().getObject(6) ? null : getRset().getInt(6));
                partido.setFecha(getRset().getTimestamp(7));
            }
            cerrarConexion();
            return partido;
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    @Override
    public PartidoDTO buscarPartidoporJornadayLocal(Integer jornada, EquipoDTO equipo) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a buscar Partido por jornada y local");
        }
        try {
            Map params = new HashMap();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT IDPARTIDO, JORNADA, LOCAL, ML, VISITANTE, MV, FECHA ");
            sql.append("FROM PARTIDOSC17 WHERE LOCAL = ? AND JORNADA = ?");
            setSql(sql);
            params.put(1, equipo.getIdEquipo());
            params.put(2, jornada);
            ejecutarQuery(params);
            PartidoDTO partido = new PartidoDTO();
            while (getRset().next()) {
                partido.setIdPartido(getRset().getInt(1));
                partido.setJornada(getRset().getInt(2));
                partido.setIdLocal(getRset().getInt(3));
                partido.setMl(null  == getRset().getObject(4) ? null : getRset().getInt(4));
                partido.setIdVisitante(getRset().getInt(5));
                partido.setMv(null  == getRset().getObject(6) ? null : getRset().getInt(6));
                partido.setFecha(getRset().getTimestamp(7));
            }
            cerrarConexion();
            return partido;
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    @Override
    public PartidoDTO buscarPartidoporEquipos(Integer id1, Integer id2) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a buscar Partido por equipos");
        }
        try {
            Map params = new HashMap();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT IDPARTIDO, JORNADA, LOCAL, ML, VISITANTE, MV, FECHA ");
            sql.append("FROM PARTIDOSC17 WHERE LOCAL = ? AND VISITANTE = ?");
            setSql(sql);
            params.put(1, id1);
            params.put(2, id2);
            ejecutarQuery(params);
            PartidoDTO partido = new PartidoDTO();
            while (getRset().next()) {
                partido.setIdPartido(getRset().getInt(1));
                partido.setJornada(getRset().getInt(2));
                partido.setIdLocal(getRset().getInt(3));
                partido.setMl(null  == getRset().getObject(4) ? null : getRset().getInt(4));
                partido.setIdVisitante(getRset().getInt(5));
                partido.setMv(null  == getRset().getObject(6) ? null : getRset().getInt(6));
                partido.setFecha(getRset().getTimestamp(7));
            }
            cerrarConexion();
            return partido;
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }
}
