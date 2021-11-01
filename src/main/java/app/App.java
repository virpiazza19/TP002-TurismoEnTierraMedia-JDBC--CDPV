package app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import daos.AtraccionDAO;
import daos.DAOFactory;
import daos.ItinerarioDAO;
import daos.PromocionDAO;
import daos.UsuarioDAO;
import tierraMedia.Atraccion;
import tierraMedia.Itinerario;
import tierraMedia.Producto;
import tierraMedia.Promocion;
import tierraMedia.Usuario;


public class App {

	public static void main(String[] args) {
		
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		System.out.println(usuarioDAO.findAll());
		System.out.println("----------------------------------");		
		
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		List<Atraccion> atracciones = atraccionDAO.findAll(); 
		System.out.println(atracciones);
		System.out.println("----------------------------------");

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		System.out.println(promocionDAO.findAllPromo(atracciones));
		System.out.println("----------------------------------");

		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		List<Producto> productos = new ArrayList<Producto>();
		System.out.println(itinerarioDAO.findAll(2, productos));
		System.out.println("----------------------------------");

		ParqueTierra parque = new ParqueTierra();
		System.out.println(parque.generarUsuarios());
		System.out.println(parque.generarProductos());
		
	}
}