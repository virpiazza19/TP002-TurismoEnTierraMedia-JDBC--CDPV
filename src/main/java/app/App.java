package app;

import dao.AtraccionDAO;
import dao.DAOFactory;
import dao.UsuarioDAO;


public class App {

	public static void main(String[] args) {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		
		System.out.println("----------------------------------");
		
		System.out.println(usuarioDAO.findAll());
		System.out.println(usuarioDAO.countAll());
		
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		
		System.out.println(atraccionDAO.findAll());
		System.out.println(atraccionDAO.countAll());
	}
}