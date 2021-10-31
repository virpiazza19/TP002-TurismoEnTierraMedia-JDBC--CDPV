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

	public List<Usuario> findAll() {
		try {
			String sql = "SELECT USUARIO.id, USUARIO.nombre, USUARIO.presupuesto, USUARIO.tiempo_disponible, Tipo_atraccion.nombre AS 'atraccion_preferida' \r\n"
					+ "FROM USUARIO\r\n "
					+ "INNER JOIN Tipo_atraccion ON Tipo_atraccion.id = Usuario.atraccion_preferida\r\n";
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
		return new Usuario(results.getInt(1), results.getString(2), results.getInt(3), results.getDouble(4),
				TipoAtraccion.valueOf(results.getString(5)));
	}
}