package app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dao.AtraccionDAO;
import dao.DAOFactory;
import dao.ItinerarioDAO;
import dao.PromocionDAO;
import dao.UsuarioDAO;
import tierraMedia.Atraccion;
<<<<<<< HEAD
import tierraMedia.Itinerario;
=======
import tierraMedia.Producto;
import tierraMedia.Promocion;
import tierraMedia.Usuario;
>>>>>>> refs/heads/Misa

public class App {

	public static void main(String[] args) {
		
		List<Usuario> usuarios = new LinkedList<Usuario>();
		List<Producto> productos = new ArrayList<Producto>();
		
		
		
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();

		System.out.println("----------------------------------");

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
		
		List <Itinerario> itinerario = itinerarioDAO.findAll();

		System.out.println(itinerario);
		List<Promocion> promociones = promocionDAO.findAllPromo(atracciones);
		
		System.out.println(promociones);
		
		for (Promocion promocion : promociones) {
			productos.add(promocion);
		}

		for (Atraccion atraccion : atracciones) {
			productos.add(atraccion);
		}
		
		
		
		System.out.println("----------------------------------");

		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();

		System.out.println(itinerarioDAO.findAll(2, productos));
		
		
		
>>>>>>> refs/heads/Misa
	}
}