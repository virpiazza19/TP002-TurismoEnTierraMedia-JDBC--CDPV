package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import conexion.ConexionProvider;
import tierraMedia.Atraccion;
import tierraMedia.Promocion;
import tierraMedia.PromocionAbsoluta;
import tierraMedia.PromocionAxB;
import tierraMedia.PromocionPorcentual;
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

	public List<Promocion> findAllPromo(List<Atraccion> atracciones) {
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

			List<Promocion> promociones = new ArrayList<Promocion>();
			Map<Promocion, List<Atraccion>> mapP = new HashMap<Promocion, List<Atraccion>>();

			while (resultados.next()) {
				if (resultados.getString("Tipo de Promocion").equals("AXB")) {
					PromocionAxB promo = promoAxB(resultados);
					agregarAtraccion(mapP, resultados, atracciones, promo);
				}

				if (resultados.getString("Tipo de Promocion").equals("ABSOLUTA")) {
					PromocionAbsoluta promo = promoAbsoluta(resultados);
					agregarAtraccion(mapP, resultados, atracciones, promo);
				}

				if (resultados.getString("Tipo de Promocion").equals("PORCENTUAL")) {
					PromocionPorcentual promo = promoPorcentual(resultados);
					agregarAtraccion(mapP, resultados, atracciones, promo);
				}
			}

			for (Map.Entry<Promocion, List<Atraccion>> entry : mapP.entrySet()) {
				entry.getKey().setAtraccionesEnPromocion(entry.getValue());
				promociones.add(entry.getKey());

			}

			return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private PromocionPorcentual promoPorcentual(ResultSet resultados) throws SQLException {
		return new PromocionPorcentual(resultados.getInt("ID"),
				TipoAtraccion.valueOf(resultados.getString("Tipo de atracciones")),
				resultados.getString("Nombre Promocion"), resultados.getDouble("Tipo descuento promocion porcentual"));
	}

	private PromocionAbsoluta promoAbsoluta(ResultSet resultados) throws SQLException {
		return new PromocionAbsoluta(resultados.getInt("ID"),
				TipoAtraccion.valueOf(resultados.getString("Tipo de atracciones")),
				resultados.getString("Nombre Promocion"), resultados.getInt("Costo total promocion absoluta"));
	}

	private PromocionAxB promoAxB(ResultSet resultados) throws SQLException {
		return new PromocionAxB(resultados.getInt("ID"),
				TipoAtraccion.valueOf(resultados.getString("Tipo de atracciones")),
				resultados.getString("Nombre Promocion"));
	}

	private Atraccion buscarAtraccion(List<Atraccion> atracciones, String nombre) {
		Atraccion a = null;
		for (Atraccion atraccion : atracciones) {
			if (atraccion.getNombre().equals(nombre)) {
				return atraccion;
			}
		}
		return a;
	}

	private void agregarAtraccion(Map<Promocion, List<Atraccion>> mapP, ResultSet resultados,
			List<Atraccion> atracciones, Promocion promo) throws SQLException {
		if (mapP.containsKey(promo)) {
			List<Atraccion> atr = mapP.get(promo);
			Atraccion a = buscarAtraccion(atracciones, resultados.getString("Atracciones incluidas"));
			atr.add(a);
			mapP.put(promo, atr);

		} else {
			List<Atraccion> atr = new ArrayList<Atraccion>();
			Atraccion a = buscarAtraccion(atracciones, resultados.getString("Atracciones incluidas"));
			atr.add(a);
			mapP.put(promo, atr);
		}
	}

	

}