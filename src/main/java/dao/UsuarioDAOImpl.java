package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import conexion.ConexionProvider;
import tierraMedia.Usuario;
import tierraMedia.TipoAtraccion;

public class UsuarioDAOImpl implements UsuarioDAO {

	public int insert(Usuario usuario) {
		
		try {
			String sql = "INSERT INTO USUARIO (ID, NOMBRE, PRESUPUESTO, TIEMPO_DISPONIBLE, ATRACCION_PREFERIDA)"
					+ "VALUES (?, ?, ?, ?, ?)";
			Connection conn = ConexionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, usuario.getId());
			statement.setString(2, usuario.getNombre());
			statement.setInt(3, usuario.getPresupuesto());
			statement.setDouble(4, usuario.getTiempoDisponible());
			statement.setString(5, String.valueOf(usuario.getAtraccionPreferida()));
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int update(Usuario usuario) {
		try {
			String sql = "UPDATE USUARIO SET PRESUPUESTO = ?, TIEMPO_DISPONIBLE = ? WHERE NOMBRE = ?";
			Connection conn = ConexionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, usuario.getPresupuesto());
			statement.setDouble(2, usuario.getTiempoDisponible());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(Usuario usuario) {
		try {
			String sql = "DELETE FROM USUARIO WHERE nombre = ?";
			Connection conn = ConexionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usuario.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Usuario findByUsername(String nombre) {
		try {
			String sql = "SELECT USUARIO.id, USUARIO.nombre, USUARIO.presupuesto, USUARIO.tiempo_disponible, Tipo_atraccion.nombre AS 'atraccion_preferida'\r\n"
					+ "FROM USUARIO "
					+ "INNER JOIN Tipo_atraccion ON Tipo_atraccion.id = Usuario.atraccion_preferida\r\n"
					+ "WHERE NOMBRE = ?";
			Connection conn = ConexionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();

			Usuario usuario = null;

			if (resultados.next()) {
				usuario = toUsuario(resultados);
			}

			return usuario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM USUARIO";
			Connection conn = ConexionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<Usuario> findAll() {
		try {
			String sql = "SELECT USUARIO.id, USUARIO.nombre, USUARIO.presupuesto, USUARIO.tiempo_disponible, Tipo_atraccion.nombre AS 'atraccion_preferida'"
					+ "FROM USUARIO "
					+ "INNER JOIN Tipo_atraccion ON Tipo_atraccion.id = Usuario.atraccion_preferida";
			Connection conn = ConexionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Usuario> usuarios = new LinkedList<Usuario>();
			while (resultados.next()) {
				usuarios.add(toUsuario(resultados));
			}

			return usuarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Usuario toUsuario(ResultSet results) throws SQLException {
		return new Usuario(results.getInt(1), results.getString(2),results.getInt(3), 
				results.getDouble(4), TipoAtraccion.valueOf(results.getString(5)));
	}
}