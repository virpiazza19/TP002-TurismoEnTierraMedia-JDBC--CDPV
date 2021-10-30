package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import conexion.ConexionProvider;
import tierraMedia.Atraccion;
import tierraMedia.Itinerario;
import tierraMedia.TipoAtraccion;

public class ItinerarioDAOImpl implements ItinerarioDAO {
	
	public int insert(Itinerario itinerario) {
		try {
			String sql = "INSERT INTO ITINERARIO (USUARIO_ID, ATRACCION_ID, PROMOCION_ID) VALUES (?, ?, ?)";
			Connection conn = ConexionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, itinerario.getUsuario());
			statement.setString(2, itinerario.getAtraccion());
			statement.setString(3, itinerario.getPromo());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<Itinerario> findAll() {
		try {
			String sql = "SELECT ATRACCION.id, ATRACCION.nombre, ATRACCION.costo, ATRACCION.duracion, ATRACCION.cupo, Tipo_atraccion.nombre AS 'tipo_atraccion'\r\n"
					+ "FROM ATRACCION\r\n"
					+ "JOIN Tipo_atraccion ON Tipo_atraccion.id = ATRACCION.tipo\r\n"
					+ "";
			Connection conn = ConexionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Itinerario> itinerario = new LinkedList<Itinerario>();
			while (resultados.next()) {
				itinerario.add(toItinerario(resultados));
			}

			return itinerario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	private Itinerario toItinerario(ResultSet resultados) throws SQLException {
		return new Itinerario(resultados.getString(1), resultados.getString(2), resultados.getString(3));
	}

	public Itinerario findByNombreUsuario(String nombre) {
		try {
			String sql = "SELECT * FROM ITINERARIO WHERE USUARIO_ID = ?";
			Connection conn = ConexionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();

			Itinerario itinerario = null;

			if (resultados.next()) {
				itinerario = toItinerario(resultados);
			}

			return itinerario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}
