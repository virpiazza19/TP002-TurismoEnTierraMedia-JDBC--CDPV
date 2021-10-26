package app;

import dao.DAOFactory;
import dao.UsuarioDAO;
import tierraMedia.TipoAtraccion;
import tierraMedia.Usuario;

public class App {

	public static void main(String[] args) {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		
		System.out.println("----------------------------------");
		Usuario Eowyn = new Usuario(154, "Eowyn", 10, 8, TipoAtraccion.AVENTURA);
		usuarioDAO.insert(Eowyn);
		
		System.out.println(usuarioDAO.findAll());
		System.out.println(usuarioDAO.countAll());
		usuarioDAO.delete(Eowyn);
		
		System.out.println(usuarioDAO.findAll());
		System.out.println(usuarioDAO.countAll());		
	}
}