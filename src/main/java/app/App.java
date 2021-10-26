package app;

import dao.AtraccionDAO;
import dao.DAOFactory;
import dao.UsuarioDAO;
import tierraMedia.TipoAtraccion;
import tierraMedia.Usuario;

public class App {

	public static void main(String[] args) {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		
		System.out.println("----------------------------------");
		Usuario as = new Usuario(9, "as", 10, 8, TipoAtraccion.AVENTURA);
		
		usuarioDAO.insert(as);
		System.out.println(usuarioDAO.findAll());
		System.out.println(usuarioDAO.countAll());
		
		usuarioDAO.delete(as);
		System.out.println(usuarioDAO.findAll());
		System.out.println(usuarioDAO.countAll());		
		
		
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		
		System.out.println(atraccionDAO.findAll());
		System.out.println(atraccionDAO.countAll());
	}
}