package app;

import dao.AtraccionDAO;
import dao.DAOFactory;
import dao.PromocionDAO;
import dao.UsuarioDAO;

public class App {

	public static void main(String[] args) {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();

		System.out.println("----------------------------------");

		System.out.println(usuarioDAO.findAll());
				
		System.out.println("----------------------------------");

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();

		System.out.println(atraccionDAO.findAll());
		
		System.out.println("----------------------------------");

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();

		System.out.println(promocionDAO.findAll());
	}
}