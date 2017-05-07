package ligamx.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ligamx.dao.JugadorDAO;
import ligamx.dto.EquipoDTO;
import ligamx.dto.JugadorDTO;
import ligamx.util.ConexionBD;
import ligamx.util.ExcepcionAplicacion;

/**
 *
 * @author hector.lopez
 */
public class JugadorDAOImpl extends ConexionBD implements JugadorDAO {

    @Override
    public boolean insertarJugador(JugadorDTO jugador, Integer idEquipo) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a insertar Jugador");
        }
        Map<Integer, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO JUGADOR (NOMBRE, IDEQUIPO, NUMERO) VALUES (?, ?, ?)");
        setSql(sql);
        params.put(1, jugador.getNombre());
        params.put(2, idEquipo);
        params.put(3, jugador.getNumero());
        return ejecutarUpdate(params);
    }

    @Override
    public boolean modificarJugador(JugadorDTO jugador, Integer idEquipo) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a modificar Jugador");
        }
        Map<Integer, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE JUGADOR SET NOMBRE = ?, IDEQUIPO = ?, NUMERO = ? WHERE IDJUGADOR = ?");
        setSql(sql);
        params.put(1, jugador.getNombre());
        params.put(2, idEquipo);
        params.put(3, jugador.getNumero());
        params.put(4, jugador.getIdJugador());
        return ejecutarUpdate(params);
    }

    @Override
    public boolean borrarJugador(JugadorDTO jugador) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a borrar Jugador");
        }
        Map<Integer, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE JUGADOR WHERE IDJUGADOR = ?");
        setSql(sql);
        params.put(1, jugador.getIdJugador());
        return ejecutarUpdate(params);
    }

    @Override
    public List<JugadorDTO> buscarJugadores(EquipoDTO equipo) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a buscar Jugadores");
        }
        try {
            Map<Integer, Object> params = new HashMap<>();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT IDJUGADOR, NOMBRE, NUMERO ");
            sql.append("FROM JUGADOR WHERE IDEQUIPO = ?");
            sql.append("ORDER BY NOMBRE");
            setSql(sql);
            params.put(1, equipo.getIdEquipo());
            ejecutarQuery(params);
            List<JugadorDTO> jugadorList = new ArrayList<>();
            while(getRset().next()) {
                JugadorDTO jugador = new JugadorDTO();
                jugador.setIdJugador(getRset().getInt(1));
                jugador.setNombre(getRset().getString(2));
                jugador.setNumero(getRset().getInt(3));
                jugadorList.add(jugador);
            }
            cerrarConexion();
            return jugadorList;
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    @Override
    public JugadorDTO buscarJugadorporId(Integer id) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a buscar Jugador por id");
        }
        try {
            Map<Integer, Object> params = new HashMap<>();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT NOMBRE, NUMERO ");
            sql.append("FROM JUGADOR WHERE IDJUGADOR = ?");
            setSql(sql);
            params.put(1, id);
            ejecutarQuery(params);
            JugadorDTO jugador = new JugadorDTO();
            jugador.setIdJugador(id);
            while(getRset().next()) {
                jugador.setNombre(getRset().getString(1));
                jugador.setNumero(getRset().getInt(2));
            }
            cerrarConexion();
            return jugador;
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    @Override
    public JugadorDTO buscarJugadorPorNombre(String jugador) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a buscar Jugador por nombre");
        }
        try {
            Map<Integer, Object> params = new HashMap<>();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT IDJUGADOR, NOMBRE, NUMERO ");
            sql.append("FROM JUGADOR WHERE NOMBRE = ?");
            setSql(sql);
            params.put(1, jugador);
            ejecutarQuery(params);
            JugadorDTO jugadorDTO = new JugadorDTO();
            while(getRset().next()) {
                jugadorDTO.setIdJugador(getRset().getInt(1));
                jugadorDTO.setNombre(getRset().getString(2));
                jugadorDTO.setNumero(getRset().getInt(3));
            }
            cerrarConexion();
            return jugadorDTO;
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

    @Override
    public JugadorDTO buscarJugadorPorId(Integer id) throws ExcepcionAplicacion {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entró a buscar Jugador por id");
        }
        try {
            Map<Integer, Object> params = new HashMap<>();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT IDJUGADOR, NOMBRE, NUMERO ");
            sql.append("FROM JUGADOR WHERE IDJUGADOR = ?");
            setSql(sql);
            params.put(1, id);
            ejecutarQuery(params);
            JugadorDTO jugadorDTO = new JugadorDTO();
            while(getRset().next()) {
                jugadorDTO.setIdJugador(getRset().getInt(1));
                jugadorDTO.setNombre(getRset().getString(2));
                jugadorDTO.setNumero(getRset().getInt(3));
            }
            cerrarConexion();
            return jugadorDTO;
        } catch (SQLException ex) {
            throw lanzarExcepcion(ex);
        }
    }

}
