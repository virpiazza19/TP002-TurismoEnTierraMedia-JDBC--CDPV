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
import tierraMedia.Producto;
import tierraMedia.TipoAtraccion;
import tierraMedia.Usuario;

public class ItinerarioDAOImpl implements ItinerarioDAO {
	
	public void insert(Producto producto, Usuario usuario) {
		try {
			if (producto.esPromo()) {
				String sql = "INSERT INTO ITINERARIO (USUARIO_ID, PROMOCION_ID) VALUES (?, ?)";
				Connection conn = ConexionProvider.getConnection();

				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, usuario.getId());
				statement.setInt(2, producto.getId());
			}
		 else {
			String sql = "INSERT INTO ITINERARIO (USUARIO_ID, ATRACCION_ID)" + "VALUES(?, ?)";
			Connection conn = ConexionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, usuario.getId());
			statement.setInt(2, producto.getId());
			statement.executeUpdate();
		}

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<Itinerario> findAll() {
		try {
			String sql = "SELECT \r\n"
					+ "(SELECT NOMBRE FROM USUARIO WHERE USUARIO.id= ITINERARIO.usuario_id) AS 'NOMBRE USUARIO',\r\n"
					+ "(SELECT NOMBRE FROM PROMOCION WHERE PROMOCION.id=ITINERARIO.promocion_id) AS 'PROMOCION COMPRADA',\r\n"
					+ "(SELECT NOMBRE FROM ATRACCION WHERE ATRACCION.id=ITINERARIO.atraccion_id) AS 'ATRACCION COMPRADA'\r\n"
					+ "FROM  Itinerario\r\n"
					+ "WHERE ITINERARIO.id IS NOT NULL"; // Se podría ordenar por nombre de usuario, o el id
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
		return new Itinerario(resultados.getInt(1), resultados.getInt(2)));
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
