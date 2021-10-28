package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import conexion.ConexionProvider;
import tierraMedia.Atraccion;
import tierraMedia.Promocion;
import tierraMedia.TipoAtraccion;
import tierraMedia.TipoPromocion;

public class PromocionDAOImpl implements PromocionDAO {

	public int update(Promocion promocion) {
		try {
			String sql = "UPDATE PROMOCION SET NOMBRE= ? WHERE ID = ?";
			Connection conn = ConexionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promocion.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<Promocion> findAll() {
		try {
			String sql = "SELECT Promocion.id AS 'ID', Promocion.nombre AS 'Nombre Promocion', Tipo_promocion.nombre AS 'Tipo de Promocion', "
					+ "Tipo_atraccion.nombre AS 'Tipo de atracciones', Atraccion.nombre AS 'Atracciones incluidas', "
					+ "Promocion.costo_total AS 'Costo total promocion absoluta', "
					+ "(select nombre from Atraccion where id = PROMOCION.atraccion_bonificada) AS 'Atraccion bonificada promocion AxB', "
					+ "Promocion.descuento AS 'Tipo descuento promocion porcentual' "
					+ "FROM Atraccion_Promocion JOIN Promocion ON Promocion.id = Atraccion_Promocion.promocion_id "
					+ "JOIN Atraccion ON Atraccion.id = Atraccion_Promocion.atraccion_id "
					+ "JOIN Tipo_atraccion ON Tipo_atraccion.id = Atraccion.tipo "
					+ "JOIN Tipo_promocion ON Tipo_promocion.id = Promocion.tipo_promo";
			Connection conn = ConexionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Promocion> promocion = new LinkedList<Promocion>();
			while (resultados.next()) {
				promocion.add(toPromocion(resultados));
			}

			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Promocion toPromocion(ResultSet results) throws SQLException {
		return new Promocion(results.getInt(1), results.getString(2), TipoPromocion.valueOf(results.getString(3)),
				TipoAtraccion.valueOf(results.getString(4)), results.getString(5), results.getInt(6),
				results.getString(7), results.getInt(8));
	}

}