package app;

import java.util.List;

import dao.AtraccionDAO;
import dao.DAOFactory;
import dao.ItinerarioDAO;
import dao.PromocionDAO;
import dao.UsuarioDAO;
import tierraMedia.Atraccion;
import tierraMedia.Itinerario;

public class App {

	public static void main(String[] args) {
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
	}
}