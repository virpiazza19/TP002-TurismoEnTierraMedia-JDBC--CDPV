package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import conexion.ConexionProvider;
import enums.TipoAtraccion;
import excepciones.MissingDataException;
import tierraMedia.Atraccion;

public class AtraccionDAO implements GenericDAO<Atraccion>{
	
	public int update(Atraccion atraccion) {
		try {
			String sql = "UPDATE ATRACCION SET CUPO= ? WHERE NOMBRE = ?";
			Connection conn = ConexionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccion.getCupo());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<Atraccion> findAll() {
		try {
			String sql = "SELECT ATRACCION.id, ATRACCION.nombre, ATRACCION.costo, ATRACCION.duracion, ATRACCION.cupo, Tipo_atraccion.nombre AS 'tipo_atraccion'\r\n"
					+ "FROM ATRACCION\r\n"
					+ "JOIN Tipo_atraccion ON Tipo_atraccion.id = ATRACCION.tipo\r\n"
					+ "";
			Connection conn = ConexionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Atraccion> atraccion = new LinkedList<Atraccion>();
			while (resultados.next()) {
				atraccion.add(toAtraccion(resultados));
			}

			return atraccion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Atraccion toAtraccion(ResultSet results) throws SQLException {
		return new Atraccion(results.getInt(1), results.getString(2), results.getInt(3), results.getDouble(4), 
				results.getInt(5), TipoAtraccion.valueOf(results.getString(6)));
	}

	
}